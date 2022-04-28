package moe.kurenai.bgm

import com.fasterxml.jackson.core.type.TypeReference
import moe.kurenai.bgm.model.character.CharacterPerson
import moe.kurenai.bgm.model.person.PersonDetail
import moe.kurenai.bgm.model.subject.RelatedPerson
import moe.kurenai.bgm.model.subject.Subject
import moe.kurenai.bgm.util.DefaultMapper.MAPPER
import kotlin.test.Test

class MapperTest {

    @Test
    fun testRelatedPerson() {
        val json =
            "[{\"id\":11467,\"name\":\"ライデンフィルム\",\"type\":1,\"career\":[\"producer\"],\"images\":{\"large\":\"https://lain.bgm.tv/pic/crt/l/a7/bb/11467_prsn_xgOFX.jpg\",\"medium\":\"https://lain.bgm.tv/pic/crt/m/a7/bb/11467_prsn_xgOFX.jpg\",\"small\":\"https://lain.bgm.tv/pic/crt/s/a7/bb/11467_prsn_xgOFX.jpg\",\"grid\":\"https://lain.bgm.tv/pic/crt/g/a7/bb/11467_prsn_xgOFX.jpg\"},\"relation\":\"动画制作\"},{\"id\":5734,\"name\":\"Steve Paul Jobs\",\"type\":1,\"career\":[],\"images\":{\"large\":\"https://lain.bgm.tv/pic/crt/l/e1/ae/5734_prsn_O2EOL.jpg\",\"medium\":\"https://lain.bgm.tv/pic/crt/m/e1/ae/5734_prsn_O2EOL.jpg\",\"small\":\"https://lain.bgm.tv/pic/crt/s/e1/ae/5734_prsn_O2EOL.jpg\",\"grid\":\"https://lain.bgm.tv/pic/crt/g/e1/ae/5734_prsn_O2EOL.jpg\"},\"relation\":\"友情協力\"},{\"id\":14720,\"name\":\"中村直人\",\"type\":1,\"career\":[\"producer\"],\"images\":null,\"relation\":\"原画\"},{\"id\":3626,\"name\":\"中村亮介\",\"type\":1,\"career\":[\"producer\"],\"images\":{\"large\":\"https://lain.bgm.tv/pic/crt/l/0e/e8/3626_prsn_anidb.jpg\",\"medium\":\"https://lain.bgm.tv/pic/crt/m/0e/e8/3626_prsn_anidb.jpg\",\"small\":\"https://lain.bgm.tv/pic/crt/s/0e/e8/3626_prsn_anidb.jpg\",\"grid\":\"https://lain.bgm.tv/pic/crt/g/0e/e8/3626_prsn_anidb.jpg\"},\"relation\":\"原画\"},{\"id\":12051,\"name\":\"小木曽伸吾\",\"type\":1,\"career\":[\"producer\"],\"images\":null,\"relation\":\"原画\"},{\"id\":1208,\"name\":\"浜崎博嗣\",\"type\":1,\"career\":[\"producer\"],\"images\":{\"large\":\"https://lain.bgm.tv/pic/crt/l/50/6b/1208_prsn_EyD7N.jpg\",\"medium\":\"https://lain.bgm.tv/pic/crt/m/50/6b/1208_prsn_EyD7N.jpg\",\"small\":\"https://lain.bgm.tv/pic/crt/s/50/6b/1208_prsn_EyD7N.jpg\",\"grid\":\"https://lain.bgm.tv/pic/crt/g/50/6b/1208_prsn_EyD7N.jpg\"},\"relation\":\"原画\"},{\"id\":12582,\"name\":\"澤田英彦\",\"type\":1,\"career\":[\"producer\"],\"images\":null,\"relation\":\"原画\"},{\"id\":12044,\"name\":\"菊池愛\",\"type\":1,\"career\":[\"producer\"],\"images\":null,\"relation\":\"原画\"},{\"id\":12049,\"name\":\"細居美恵子\",\"type\":1,\"career\":[\"producer\",\"illustrator\"],\"images\":{\"large\":\"https://lain.bgm.tv/pic/crt/l/d5/29/12049_prsn_xdEZr.jpg\",\"medium\":\"https://lain.bgm.tv/pic/crt/m/d5/29/12049_prsn_xdEZr.jpg\",\"small\":\"https://lain.bgm.tv/pic/crt/s/d5/29/12049_prsn_xdEZr.jpg\",\"grid\":\"https://lain.bgm.tv/pic/crt/g/d5/29/12049_prsn_xdEZr.jpg\"},\"relation\":\"原画\"},{\"id\":3026,\"name\":\"五十嵐慎一\",\"type\":1,\"career\":[\"producer\"],\"images\":null,\"relation\":\"摄影监督\"},{\"id\":12051,\"name\":\"小木曽伸吾\",\"type\":1,\"career\":[\"producer\"],\"images\":null,\"relation\":\"作画监督\"},{\"id\":12049,\"name\":\"細居美恵子\",\"type\":1,\"career\":[\"producer\",\"illustrator\"],\"images\":{\"large\":\"https://lain.bgm.tv/pic/crt/l/d5/29/12049_prsn_xdEZr.jpg\",\"medium\":\"https://lain.bgm.tv/pic/crt/m/d5/29/12049_prsn_xdEZr.jpg\",\"small\":\"https://lain.bgm.tv/pic/crt/s/d5/29/12049_prsn_xdEZr.jpg\",\"grid\":\"https://lain.bgm.tv/pic/crt/g/d5/29/12049_prsn_xdEZr.jpg\"},\"relation\":\"作画监督\"},{\"id\":12049,\"name\":\"細居美恵子\",\"type\":1,\"career\":[\"producer\",\"illustrator\"],\"images\":{\"large\":\"https://lain.bgm.tv/pic/crt/l/d5/29/12049_prsn_xdEZr.jpg\",\"medium\":\"https://lain.bgm.tv/pic/crt/m/d5/29/12049_prsn_xdEZr.jpg\",\"small\":\"https://lain.bgm.tv/pic/crt/s/d5/29/12049_prsn_xdEZr.jpg\",\"grid\":\"https://lain.bgm.tv/pic/crt/g/d5/29/12049_prsn_xdEZr.jpg\"},\"relation\":\"总作画监督\"},{\"id\":12049,\"name\":\"細居美恵子\",\"type\":1,\"career\":[\"producer\",\"illustrator\"],\"images\":{\"large\":\"https://lain.bgm.tv/pic/crt/l/d5/29/12049_prsn_xdEZr.jpg\",\"medium\":\"https://lain.bgm.tv/pic/crt/m/d5/29/12049_prsn_xdEZr.jpg\",\"small\":\"https://lain.bgm.tv/pic/crt/s/d5/29/12049_prsn_xdEZr.jpg\",\"grid\":\"https://lain.bgm.tv/pic/crt/g/d5/29/12049_prsn_xdEZr.jpg\"},\"relation\":\"人物设定\"},{\"id\":3596,\"name\":\"村井秀清\",\"type\":1,\"career\":[\"producer\",\"artist\"],\"images\":{\"large\":\"https://lain.bgm.tv/pic/crt/l/52/c4/3596_prsn_anidb.jpg\",\"medium\":\"https://lain.bgm.tv/pic/crt/m/52/c4/3596_prsn_anidb.jpg\",\"small\":\"https://lain.bgm.tv/pic/crt/s/52/c4/3596_prsn_anidb.jpg\",\"grid\":\"https://lain.bgm.tv/pic/crt/g/52/c4/3596_prsn_anidb.jpg\"},\"relation\":\"音乐\"},{\"id\":3626,\"name\":\"中村亮介\",\"type\":1,\"career\":[\"producer\"],\"images\":{\"large\":\"https://lain.bgm.tv/pic/crt/l/0e/e8/3626_prsn_anidb.jpg\",\"medium\":\"https://lain.bgm.tv/pic/crt/m/0e/e8/3626_prsn_anidb.jpg\",\"small\":\"https://lain.bgm.tv/pic/crt/s/0e/e8/3626_prsn_anidb.jpg\",\"grid\":\"https://lain.bgm.tv/pic/crt/g/0e/e8/3626_prsn_anidb.jpg\"},\"relation\":\"演出\"},{\"id\":3626,\"name\":\"中村亮介\",\"type\":1,\"career\":[\"producer\"],\"images\":{\"large\":\"https://lain.bgm.tv/pic/crt/l/0e/e8/3626_prsn_anidb.jpg\",\"medium\":\"https://lain.bgm.tv/pic/crt/m/0e/e8/3626_prsn_anidb.jpg\",\"small\":\"https://lain.bgm.tv/pic/crt/s/0e/e8/3626_prsn_anidb.jpg\",\"grid\":\"https://lain.bgm.tv/pic/crt/g/0e/e8/3626_prsn_anidb.jpg\"},\"relation\":\"分镜\"},{\"id\":3626,\"name\":\"中村亮介\",\"type\":1,\"career\":[\"producer\"],\"images\":{\"large\":\"https://lain.bgm.tv/pic/crt/l/0e/e8/3626_prsn_anidb.jpg\",\"medium\":\"https://lain.bgm.tv/pic/crt/m/0e/e8/3626_prsn_anidb.jpg\",\"small\":\"https://lain.bgm.tv/pic/crt/s/0e/e8/3626_prsn_anidb.jpg\",\"grid\":\"https://lain.bgm.tv/pic/crt/g/0e/e8/3626_prsn_anidb.jpg\"},\"relation\":\"脚本\"},{\"id\":3626,\"name\":\"中村亮介\",\"type\":1,\"career\":[\"producer\"],\"images\":{\"large\":\"https://lain.bgm.tv/pic/crt/l/0e/e8/3626_prsn_anidb.jpg\",\"medium\":\"https://lain.bgm.tv/pic/crt/m/0e/e8/3626_prsn_anidb.jpg\",\"small\":\"https://lain.bgm.tv/pic/crt/s/0e/e8/3626_prsn_anidb.jpg\",\"grid\":\"https://lain.bgm.tv/pic/crt/g/0e/e8/3626_prsn_anidb.jpg\"},\"relation\":\"导演\"},{\"id\":9915,\"name\":\"茶麻\",\"type\":1,\"career\":[\"mangaka\"],\"images\":{\"large\":\"https://lain.bgm.tv/pic/crt/l/69/5f/9915_prsn_XomXX.jpg\",\"medium\":\"https://lain.bgm.tv/pic/crt/m/69/5f/9915_prsn_XomXX.jpg\",\"small\":\"https://lain.bgm.tv/pic/crt/s/69/5f/9915_prsn_XomXX.jpg\",\"grid\":\"https://lain.bgm.tv/pic/crt/g/69/5f/9915_prsn_XomXX.jpg\"},\"relation\":\"原作\"},{\"id\":3626,\"name\":\"中村亮介\",\"type\":1,\"career\":[\"producer\"],\"images\":{\"large\":\"https://lain.bgm.tv/pic/crt/l/0e/e8/3626_prsn_anidb.jpg\",\"medium\":\"https://lain.bgm.tv/pic/crt/m/0e/e8/3626_prsn_anidb.jpg\",\"small\":\"https://lain.bgm.tv/pic/crt/s/0e/e8/3626_prsn_anidb.jpg\",\"grid\":\"https://lain.bgm.tv/pic/crt/g/0e/e8/3626_prsn_anidb.jpg\"},\"relation\":\"OP・ED 分镜\"},{\"id\":1208,\"name\":\"浜崎博嗣\",\"type\":1,\"career\":[\"producer\"],\"images\":{\"large\":\"https://lain.bgm.tv/pic/crt/l/50/6b/1208_prsn_EyD7N.jpg\",\"medium\":\"https://lain.bgm.tv/pic/crt/m/50/6b/1208_prsn_EyD7N.jpg\",\"small\":\"https://lain.bgm.tv/pic/crt/s/50/6b/1208_prsn_EyD7N.jpg\",\"grid\":\"https://lain.bgm.tv/pic/crt/g/50/6b/1208_prsn_EyD7N.jpg\"},\"relation\":\"OP・ED 分镜\"}]"
        println(MAPPER.readValue(json, object : TypeReference<List<RelatedPerson>>() {}))
    }

    @Test
    fun testPersonDetail() {
        val json =
            "{\"id\":12044,\"name\":\"菊池愛\",\"type\":1,\"career\":[\"producer\"],\"images\":null,\"summary\":\"スタジオ ラーブ出身，埼玉県蓮田市出身.\",\"locked\":false,\"last_modified\":\"2020-06-20T01:35:14+00:00\",\"infobox\":[{\"key\":\"简体中文名\",\"value\":\"菊池爱\"},{\"key\":\"别名\",\"value\":[{\"k\":\"纯假名\",\"v\":\"きくち あい\"},{\"k\":\"罗马字\",\"v\":\"Kikuchi Ai\"}]},{\"key\":\"性别\",\"value\":\"女\"},{\"key\":\"生日\",\"value\":\"1971-12-24\"}],\"gender\":\"female\",\"blood_type\":null,\"birth_year\":null,\"birth_mon\":null,\"birth_day\":null,\"stat\":{\"comments\":3,\"collects\":30},\"img\":null}"
        println(MAPPER.readValue(json, PersonDetail::class.java))
        val json2 =
            "{\"id\":9915,\"name\":\"茶麻\",\"type\":1,\"career\":[\"mangaka\"],\"images\":{\"large\":\"https://lain.bgm.tv/pic/crt/l/69/5f/9915_prsn_XomXX.jpg\",\"medium\":\"https://lain.bgm.tv/pic/crt/m/69/5f/9915_prsn_XomXX.jpg\",\"small\":\"https://lain.bgm.tv/pic/crt/s/69/5f/9915_prsn_XomXX.jpg\",\"grid\":\"https://lain.bgm.tv/pic/crt/g/69/5f/9915_prsn_XomXX.jpg\"},\"summary\":\"らき☆すた・バンブーブレード・涼宮ハルヒの憂鬱・VOCALOIDなど、幅広いキャラクターを使った若干シュールなギャグマンガを描く。デフォルメが上手く画力も高い。またBGMやSEの使い方も上手い。\\r\\n\\r\\n最初の頃は投稿された作品内から選択された普通のサムネだったが、途中から何を思ったのか逆手で描いたと思しき絵をサムネ用として書き下ろし、その絵が毎回サムネを飾ることとなる。どんな判断だ、評価をドブに捨てる気か\\r\\n また、そのわざと視聴を拒むかのようなサムネ絵から「うｐ主は評価されると死ぬ病気」のタグが付き始めた。しかし逆手で描き続けた成果か、サムネ絵にも画力の向上が見られる。\\r\\n\\r\\n現在Pixivでも活動中。\\r\\n\\r\\nまた、2011年3月よりニコニコ静画内の「ニコニコ漫画」にて『あいうら』を連載中。\",\"locked\":false,\"last_modified\":\"1970-01-01T00:00:00+00:00\",\"infobox\":[{\"key\":\"twitter\",\"value\":\"@chamasuke\"}],\"gender\":\"male\",\"blood_type\":null,\"birth_year\":null,\"birth_mon\":null,\"birth_day\":null,\"stat\":{\"comments\":0,\"collects\":1},\"img\":\"https://lain.bgm.tv/pic/crt/m/69/5f/9915_prsn_XomXX.jpg\"}"
        println(MAPPER.readValue(json2, PersonDetail::class.java))
        val json3 =
            "{\"id\":3626,\"name\":\"中村亮介\",\"type\":1,\"career\":[\"producer\"],\"images\":{\"large\":\"https://lain.bgm.tv/pic/crt/l/0e/e8/3626_prsn_anidb.jpg\",\"medium\":\"https://lain.bgm.tv/pic/crt/m/0e/e8/3626_prsn_anidb.jpg\",\"small\":\"https://lain.bgm.tv/pic/crt/s/0e/e8/3626_prsn_anidb.jpg\",\"grid\":\"https://lain.bgm.tv/pic/crt/g/0e/e8/3626_prsn_anidb.jpg\"},\"summary\":\"アニメーション監督、アニメーション演出家。\\r\\n\\r\\n東京大学卒業後、1999年にマッドハウスに入社する。『MONSTER』で監督助手を務め、『魍魎の匣』で初監督。現在はフリーの演出家として活動している。既婚。\\r\\n\\r\\n原作物のアニメーションを手掛ける際には、原作に対して誠意を持って向き合い、作中のエッセンスを表現するために「原作に忠実であるよりも誠実に」という姿勢を心掛けている。雲井一夢の名義での活動もある。座右の銘は「感謝」と「等身大の自分」。\",\"locked\":false,\"last_modified\":\"2021-04-19T13:36:12+00:00\",\"infobox\":[{\"key\":\"简体中文名\",\"value\":\"中村亮介\"},{\"key\":\"别名\",\"value\":[{\"v\":\"雲井一夢\"},{\"k\":\"纯假名\",\"v\":\"なかむら りょうすけ\"},{\"k\":\"罗马字\",\"v\":\"Nakamura Ryousuke\"}]},{\"key\":\"性别\",\"value\":\"男\"},{\"key\":\"生日\",\"value\":\"1976年5月27日\"}],\"gender\":null,\"blood_type\":null,\"birth_year\":null,\"birth_mon\":null,\"birth_day\":null,\"stat\":{\"comments\":29,\"collects\":179},\"img\":\"https://lain.bgm.tv/pic/crt/m/0e/e8/3626_prsn_anidb.jpg\"}"
        println(MAPPER.readValue(json3, PersonDetail::class.java))
    }

    @Test
    fun testCharacterDetail() {
        val json =
            "{\"id\":19683,\"name\":\"天谷奏香\",\"type\":1,\"images\":{\"large\":\"https://lain.bgm.tv/pic/crt/l/3e/27/19683_crt_7LeOu.jpg\",\"medium\":\"https://lain.bgm.tv/pic/crt/m/3e/27/19683_crt_7LeOu.jpg\",\"small\":\"https://lain.bgm.tv/pic/crt/s/3e/27/19683_crt_7LeOu.jpg\",\"grid\":\"https://lain.bgm.tv/pic/crt/g/3e/27/19683_crt_7LeOu.jpg\"},\"summary\":\"\",\"locked\":false,\"infobox\":[{\"key\":\"简体中文名\",\"value\":\"天谷奏香\"},{\"key\":\"别名\",\"value\":[{\"k\":\"罗马字\",\"v\":\"Amaya Kanaka\"}]},{\"key\":\"生日\",\"value\":\"11月10日\"}],\"gender\":null,\"blood_type\":null,\"birth_year\":null,\"birth_mon\":11,\"birth_day\":10,\"stat\":{\"comments\":4,\"collects\":28}}"
        println(MAPPER.readValue(json, CharacterPerson::class.java))
    }

    @Test
    fun testDeserialize() {
        val json = "[\n" +
            "    \"moe.kurenai.bgm.model.character.CharacterDetail\",\n" +
            "    {\n" +
            "        \"id\": 20061,\n" +
            "        \"name\": \"山下寿美子\",\n" +
            "        \"type\": 1,\n" +
            "        \"images\": [\n" +
            "            \"moe.kurenai.bgm.model.subject.Image\",\n" +
            "            {\n" +
            "                \"large\": \"https://lain.bgm.tv/pic/crt/l/23/6a/20061_crt_4tNiW.jpg\",\n" +
            "                \"common\": \"https://bgm.tv/img/no_icon_subject.png\",\n" +
            "                \"medium\": \"https://lain.bgm.tv/pic/crt/m/23/6a/20061_crt_4tNiW.jpg\",\n" +
            "                \"small\": \"https://lain.bgm.tv/pic/crt/s/23/6a/20061_crt_4tNiW.jpg\",\n" +
            "                \"grid\": \"https://lain.bgm.tv/pic/crt/g/23/6a/20061_crt_4tNiW.jpg\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"summary\": \"\",\n" +
            "        \"locked\": false,\n" +
            "        \"infoBox\": null,\n" +
            "        \"bloodType\": 0,\n" +
            "        \"gender\": \"female\",\n" +
            "        \"birthYear\": null,\n" +
            "        \"birthMon\": null,\n" +
            "        \"birthDay\": null,\n" +
            "        \"stat\": [\n" +
            "            \"moe.kurenai.bgm.model.character.Stat\",\n" +
            "            {\n" +
            "                \"comments\": 1,\n" +
            "                \"collects\": 2\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "]"
        println(MAPPER.readValue(json, Object::class.java))
    }

    @org.junit.Test
    fun testInfoBox() {
        val json =
            "{\"date\":\"2022-04-06\",\"platform\":\"TV\",\"images\":{\"small\":\"https://lain.bgm.tv/pic/cover/s/5f/e2/366253_9Q1aR.jpg\",\"grid\":\"https://lain.bgm.tv/pic/cover/g/5f/e2/366253_9Q1aR.jpg\",\"large\":\"https://lain.bgm.tv/pic/cover/l/5f/e2/366253_9Q1aR.jpg\",\"medium\":\"https://lain.bgm.tv/pic/cover/m/5f/e2/366253_9Q1aR.jpg\",\"common\":\"https://lain.bgm.tv/pic/cover/c/5f/e2/366253_9Q1aR.jpg\"},\"summary\":\"「生きるのがツライ？ なら逃げちゃえばいいんですよ」\\r\\nずっと先の未来。人間はそれまでの姿形だけでなく、獣人・サイボーグ・魔族など多様な姿を持つようになった。東京の街は、AIが管理する高い壁に囲まれた数多の地域「クラスタ」となり、自由な行き来をやめ、それぞれが独自の文化・常識を育んだ。人々は、自らが生まれたクラスタの常識を基準に幸せな人生を送る。\\r\\n\\r\\nしかし、なかには自らのクラスタに適応できない者も現れる──。\\r\\nそうした人々を、別のクラスタへと「逃がす」ことを生業にする者たちがいる。\\r\\n「逃げたい人」たちから依頼を受け、あらゆる方法を駆使してAIの裏をかき、本来は不可能であるクラスタ間の移動を成し遂げる者たち──「逃がし屋」。\\r\\n\\r\\n逃げて、逃げて、逃げまくる！！\\r\\n逃げたい人をお手伝いする、5人の逃がし屋たちの物語──！\",\"name\":\"エスタブライフ グレイトエスケープ\",\"name_cn\":\"Estab Life Great Escape\",\"tags\":[{\"name\":\"原创\",\"count\":84},{\"name\":\"PolygonPictures\",\"count\":72},{\"name\":\"2022年4月\",\"count\":71},{\"name\":\"TV\",\"count\":54},{\"name\":\"谷口悟朗\",\"count\":45},{\"name\":\"3D\",\"count\":43},{\"name\":\"2022\",\"count\":35},{\"name\":\"科幻\",\"count\":28},{\"name\":\"橋本裕之\",\"count\":21},{\"name\":\"百合\",\"count\":19},{\"name\":\"SF\",\"count\":14},{\"name\":\"賀東招二\",\"count\":14},{\"name\":\"2022年\",\"count\":6},{\"name\":\"搞笑\",\"count\":6},{\"name\":\"嶺内ともみ\",\"count\":4},{\"name\":\"22春\",\"count\":2},{\"name\":\"TVA\",\"count\":2},{\"name\":\"战斗\",\"count\":2},{\"name\":\"润\",\"count\":2},{\"name\":\"2022春\",\"count\":1},{\"name\":\"8分\",\"count\":1},{\"name\":\"D02\",\"count\":1},{\"name\":\"PPI\",\"count\":1},{\"name\":\"三渲二贺东招二\",\"count\":1},{\"name\":\"乐\",\"count\":1},{\"name\":\"可独立观赏\",\"count\":1},{\"name\":\"尼给路达哟，\",\"count\":1},{\"name\":\"未上映\",\"count\":1},{\"name\":\"長縄まりあ\",\"count\":1},{\"name\":\"，\",\"count\":1}],\"infobox\":[{\"key\":\"中文名\",\"value\":\"Estab Life Great Escape\"},{\"key\":\"别名\",\"value\":[{\"v\":\"ESTABLISHMENT IN LIFE GREAT ESCAPE\"}]},{\"key\":\"话数\",\"value\":\"12\"},{\"key\":\"放送开始\",\"value\":\"2022年4月6日\"},{\"key\":\"放送星期\",\"value\":\"星期三\"},{\"key\":\"官方网站\",\"value\":\"https://establife.tokyo/\"},{\"key\":\"播放电视台\",\"value\":\"フジテレビ「＋Ultra」\"},{\"key\":\"其他电视台\",\"value\":\"関西テレビ / 東海テレビ / 北海道文化放送 / テレビ西日本 / BSフジ\"},{\"key\":\"播放结束\",\"value\":\"2022年4月12日（FOD）\"},{\"key\":\"其他\",\"value\":\"2022年3月1日（FOD）先行配信\"},{\"key\":\"Copyright\",\"value\":\"©SSF/エスタブライフ製作委員会\"},{\"key\":\"原案\",\"value\":\"谷口悟朗\"},{\"key\":\"创意统括\",\"value\":\"谷口悟朗\"},{\"key\":\"原作\",\"value\":\"SSF\"},{\"key\":\"导演\",\"value\":\"橋本裕之\"},{\"key\":\"系列构成\",\"value\":\"賀東招二\"},{\"key\":\"脚本\",\"value\":\"賀東招二\"},{\"key\":\"人物原案\",\"value\":\"コザキユースケ\"},{\"key\":\"概念艺术\",\"value\":\"富安健一郎(INEI)\"},{\"key\":\"音乐\",\"value\":\"藤澤慶昌\"},{\"key\":\"企画\",\"value\":\"スロウカーブ\"},{\"key\":\"制片人\",\"value\":\"スロウカーブ\"},{\"key\":\"动画制作\",\"value\":\"ポリゴン・ピクチュアズ\"},{\"key\":\"主题歌演出\",\"value\":\"めいちゃん、GOOD ON THE REEL\"},{\"key\":\"CG 导演\",\"value\":\"坂間健太、関水大樹、上本雅之\"},{\"key\":\"美术监督\",\"value\":\"高橋佐知、島村大輔\"},{\"key\":\"色彩设计\",\"value\":\"野地弘納\"}],\"rating\":{\"rank\":2233,\"total\":125,\"count\":{\"1\":0,\"2\":3,\"3\":0,\"4\":3,\"5\":9,\"6\":17,\"7\":50,\"8\":31,\"9\":6,\"10\":6},\"score\":7},\"total_episodes\":12,\"collection\":{\"on_hold\":18,\"dropped\":47,\"wish\":189,\"collect\":12,\"doing\":433},\"id\":366253,\"eps\":12,\"volumes\":0,\"locked\":false,\"nsfw\":false,\"type\":2}"
        println(MAPPER.readValue(json, Subject::class.java))
    }
}
