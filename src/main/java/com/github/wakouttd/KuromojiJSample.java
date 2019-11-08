package com.github.wakouttd;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.codelibs.neologd.ipadic.lucene.analysis.ja.JapaneseTokenizer;
import org.codelibs.neologd.ipadic.lucene.analysis.ja.tokenattributes.PartOfSpeechAttribute;


public class KuromojiJSample {

    private List<String> kuromojineologd(String src) {
        List<String> results = new ArrayList<>();
        try (JapaneseTokenizer tokenizer = new JapaneseTokenizer(
                null, // ユーザ辞書使わず
                true, // 記号は無視
                JapaneseTokenizer.Mode.NORMAL
        )) {
            CharTermAttribute term = tokenizer.addAttribute(CharTermAttribute.class);
            PartOfSpeechAttribute part = tokenizer.addAttribute(PartOfSpeechAttribute.class);

            tokenizer.setReader(new StringReader(src));
            tokenizer.reset();
            while (tokenizer.incrementToken()) {
                results.add(term.toString()+ ":" + part.getPartOfSpeech());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static void main(String[] args) {
        KuromojiJSample sample = new KuromojiJSample();
        sample.execute();
    }

    public void execute() {
        System.out.println(kuromojineologd("ピコ太郎さんはカナブンに角をつけてカブトムシとして売るバイトをしている"));
        System.out.println(kuromojineologd("リーチマイケルが、相手チームのロッカールームを訪ねたという。ロシア選手の健闘をたたえ、模造刀をわたすためだった。"));
        System.out.println(kuromojineologd("EXIT兼近、“完成間近のクリス松村”姿を公開　「クオリティ高すぎww」「チャラ村最高」の声"));
        System.out.println(kuromojineologd("新語・流行語大賞ノミネート、サブスクリプション、キャッシュレス、タピる、令和、ラグビーワールドカップ、にわかファン、ONE TEAM、タピオカドリンク、米津玄師、笑わない男、スマイリングシンデレラ"));
        System.out.println(kuromojineologd("チャンピオンシップ"));
        System.out.println(kuromojineologd("れいわ新選組や、れいわ旋風など、令和にちなんだ命名が多く見られました"));
    }

}
