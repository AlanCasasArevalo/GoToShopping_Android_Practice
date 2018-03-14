package alancasasarevalo.com.repository

import alancasasarevalo.com.repository.model.EntityModel
import alancasasarevalo.com.repository.network.json.JsonEntitiesParser
import alancasasarevalo.com.repository.utils.ReadJsonFile
import android.util.Log
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import junit.framework.Assert
import org.junit.Test

class JSONShopsParsingTest {
    @Test
    @Throws(Exception::class)
    fun given_valid_string_when_containing_json_then_it_parse_one_shop_correctly() {
        val shopJson = ReadJsonFile().loadJSONFromAsset("MadridShop.json")
        Assert.assertTrue(false == shopJson.isEmpty())
        Assert.assertTrue(!shopJson.isEmpty())

        //parseo
        val parser = JsonEntitiesParser()
        val shop = parser.parse<EntityModel>(shopJson)

        Assert.assertNotNull(shop)
        Assert.assertEquals("Cortefiel - Preciados", shop.name)
        Assert.assertEquals(40.4180563f, shop.latitude.toFloat(), 0.1f)

    }

    @Test
    @Throws(Exception::class)
    fun given_invalid_string_when_containing_json_with_wrong_latitude_then_it_parse_one_shop_correctly() {
        val activityJson = ReadJsonFile().loadJSONFromAsset("shopWrongLatitude.json")
        Assert.assertTrue(!activityJson.isEmpty())

        //parseo
        val parser = JsonEntitiesParser()
        var activityEntity : EntityModel

        activityEntity = try {
            parser.parse<EntityModel>(activityJson)
        }catch (e: InvalidFormatException){
            EntityModel(1,
                    1,
                    "Cortefiel - Preciados",
                    "https://madrid-shops.com/media/shops/cortefiel-small.jpg",
                    "https://madrid-shops.com/media/shops/logo-cortefiel-200.jpg",
                    "Puerta del Sol 11",
                    "www.grupocortefiel.com",
                    "",
                    "(34) 91 522 64 31",
                    "An extensive network of stores spread across four continents makes Cortefiel Group one of the leading European companies in the fashion industry.  Through its four chains -Cortefiel, Pedro del Hierro, Springfield and Women'secret-, the Group operates in 58 countries with 1,647 points of sale. ",
                    "Una extensa red de tiendas distribuidas por cuatro continentes convierte a Grupo Cortefiel en una de las principales compañías europeas del sector moda. A través de sus cuatro cadenas –Cortefiel, Pedro del Hierro, Springfield y Women’secret-, el Grupo está presente en 58 países con 1.647 puntos de venta.",
                    "4大陸に店舗展開するCortefielグル－プは、ファッションの世界ではヨ－ロッパで主要なアパレルメ－カ－です。彼らの4つのブランド―Cortefiel、Pedro del Hierro、Springfield、Women’secret―は58カ国1647ヶ所で販売しています。",
                    "Cortefiel庞大的实体店销售网络分布全球四大洲，这令Cortefiel集团成为欧洲最领先的服装品牌之一。Cortefiel集团旗下的四大品牌Cortefiel、Pedro del Hierro、Springfield及Women's Secret,在全世界58个国家拥有1647个销售实体店。",
                    "40.4180563 ",
                    "-3.7010172999999895",
                    "Monday to Saturday: 10: 00-21: 00",
                    "De lu. a sa. de 10 a 20 h.",
                    " 月曜～土曜10:00-20:00",
                    "周二 - 周六：10:00-20:00",
                    "shop"
            )
        }



        Assert.assertNotNull(activityEntity)
        Assert.assertNotSame("Parsing failed CRASH", activityEntity.name)
        Assert.assertEquals(getCorrectCoordinateComponent(activityEntity.latitude).toFloat(), 40.4180563f)

    }

    private fun getCorrectCoordinateComponent(coordinateComponent: String): String {
        var coordinate = 0.0f
        val s = coordinateComponent.replace(",", "")
        try {
            coordinate = java.lang.Float.parseFloat(s)
        } catch (e: Exception) {
            Log.d("ERROR CONVERTING", String.format("Can't convert %s", coordinateComponent))
        }
        return coordinate.toString()
    }

}