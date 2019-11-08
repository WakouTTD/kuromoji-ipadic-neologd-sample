package com.github.wakouttd

import java.io.IOException
import java.io.StringReader
import java.util

import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import org.codelibs.neologd.ipadic.lucene.analysis.ja.JapaneseTokenizer
import org.codelibs.neologd.ipadic.lucene.analysis.ja.tokenattributes.PartOfSpeechAttribute


object KuromojiSSample {
  def main(args: Array[String]): Unit = {

    val texts = List(
        "ピコ太郎さんはカナブンに角をつけてカブトムシとして売るバイトをしている",
        "リーチマイケルが、相手チームのロッカールームを訪ねたという。ロシア選手の健闘をたたえ、模造刀をわたすためだった。",
        "EXIT兼近、“完成間近のクリス松村”姿を公開　「クオリティ高すぎww」「チャラ村最高」の声",
        "新語・流行語大賞ノミネート、サブスクリプション、キャッシュレス、タピる、令和、ラグビーワールドカップ、にわかファン、ONE TEAM、タピオカドリンク、米津玄師、笑わない男、スマイリングシンデレラ",
        "チャンピオンシップ",
        "れいわ新選組や、れいわ旋風など、令和にちなんだ命名が多く見られました"
      )

    val modes = List(
//        JapaneseTokenizer.Mode.SEARCH,
        JapaneseTokenizer.Mode.NORMAL//,
//        JapaneseTokenizer.Mode.EXTENDED
      )

    for {
      text <- texts
      mode <- modes
    } withJapaneseTokenizer(text = text, mode = mode)(displayTokens)
  }

  def withJapaneseTokenizer(text: String, mode: JapaneseTokenizer.Mode)(body: (String, TokenStream) => Unit): Unit = {
    val tokenizer = new JapaneseTokenizer(
      null,
      true,
      mode
    )
    tokenizer.setReader(new StringReader(text))

    println(s"Mode => $mode Start")

    try {
      body(text, tokenizer)
    } finally {
      tokenizer.close()
    }

    println(s"Mode => $mode End")
    println()
  }

  def displayTokens(text: String, tokenStream: TokenStream) = {
    val charTermAttr = tokenStream.addAttribute(classOf[CharTermAttribute])

    println("<<==========================================")
    println(s"input text => $text")
    println("============================================")

    tokenStream.reset()

    while (tokenStream.incrementToken()) {
      val token = charTermAttr.toString
      println(s"token: $token")
    }

    tokenStream.close()
  }



}
