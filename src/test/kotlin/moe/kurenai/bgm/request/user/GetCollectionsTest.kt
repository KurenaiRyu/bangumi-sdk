package moe.kurenai.bgm.request.user

import kotlinx.coroutines.runBlocking
import moe.kurenai.bgm.BgmClientFactory.getClient
import moe.kurenai.bgm.util.DefaultMapper.MAPPER
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * @author Kurenai
 * @since 2022/10/22 21:09
 */
class GetCollectionsTest {

    private val client = getClient().coroutine()

    @Test
    fun testJson() {
        val json = """
            {
              "data": [
                {
                  "updated_at": "2022-05-31T05:20:37Z",
                  "comment": null,
                  "tags": [
                    "Netflix",
                    "2022",
                    "动画",
                    "美国"
                  ],
                  "subject": {
                    "date": "2022-05-20",
                    "images": {
                      "small": "https://lain.bgm.tv/pic/cover/s/ef/a9/333707_XoOYR.jpg",
                      "grid": "https://lain.bgm.tv/pic/cover/g/ef/a9/333707_XoOYR.jpg",
                      "large": "https://lain.bgm.tv/pic/cover/l/ef/a9/333707_XoOYR.jpg",
                      "medium": "https://lain.bgm.tv/pic/cover/m/ef/a9/333707_XoOYR.jpg",
                      "common": "https://lain.bgm.tv/pic/cover/c/ef/a9/333707_XoOYR.jpg"
                    },
                    "name": "Love, Death &amp; Robots Volume 3",
                    "name_cn": "爱、死亡 &amp; 机器人 第三季",
                    "short_summary": "　　艾美奖获奖动画选集《爱、死亡 & 机器人》第三部回归，由蒂姆·米勒（《死侍》《终结者：黑暗命运》）和大卫·芬奇（《心灵猎人》《曼克》）担任监制。恐怖、想象力和美在新剧集中完美融合，从揭示古老的邪恶力量到喜剧般的末日，剧集以标志性的巧思和",
                    "tags": [
                      {
                        "name": "Netflix",
                        "count": 297
                      },
                      {
                        "name": "2022",
                        "count": 209
                      },
                      {
                        "name": "原创",
                        "count": 201
                      },
                      {
                        "name": "欧美",
                        "count": 193
                      },
                      {
                        "name": "科幻",
                        "count": 172
                      },
                      {
                        "name": "短片",
                        "count": 142
                      },
                      {
                        "name": "WEB",
                        "count": 99
                      },
                      {
                        "name": "爱、死亡&机器人",
                        "count": 83
                      },
                      {
                        "name": "短片集",
                        "count": 80
                      },
                      {
                        "name": "2022年4月",
                        "count": 37
                      }
                    ],
                    "score": 7.1,
                    "type": 2,
                    "id": 333707,
                    "eps": 9,
                    "volumes": 0,
                    "collection_total": 1788,
                    "rank": 2213
                  },
                  "subject_id": 333707,
                  "vol_status": 0,
                  "ep_status": 6,
                  "subject_type": 2,
                  "type": 3,
                  "rate": 0,
                  "private": false
                }
              ],
              "total": 31,
              "limit": 1,
              "offset": 0
            }
        """.trimIndent()
        MAPPER.readValue(json, GetCollections.jacksonType)
    }

    @Test
    fun testCoroutine(): Unit = runBlocking {
        val page = client.send(GetCollections("sai"))
        assertTrue(page.total >= 1)
        assertTrue(page.data.isNotEmpty())
    }
}
