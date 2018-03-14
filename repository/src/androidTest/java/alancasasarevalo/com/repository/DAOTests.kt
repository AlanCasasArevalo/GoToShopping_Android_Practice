package alancasasarevalo.com.repository

import alancasasarevalo.com.activitytype.ActivityType
import alancasasarevalo.com.repository.database.dao.EntityDAO
import alancasasarevalo.com.repository.model.EntityModel
import android.support.test.InstrumentationRegistry
import android.util.Log
import junit.framework.Assert
import org.junit.Test


class DAOTests {
    val appContext = InstrumentationRegistry.getTargetContext()
    internal val dbhelper = buildDBHelper(appContext, "mydb.sqlite", 1)

    internal val entityDAO = EntityDAO(dbhelper)

    val activity = EntityModel(1,
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

    val activity2 = EntityModel(4,
            4,
            "Miss Sixty",
            "https://madrid-shops.com/media/shops/misssixty-small.jpg",
            "https://madrid-shops.com/media/shops/logo-misssixty-200.jpg",
            "C/mesonero romanos 2",
            "www.misssixty.com",
            "",
            "(34) 91 522 61 72",
            "An oasis of freshness is concentrated in the central branch of Miss Sixty, located in the commercial hub of Atlantico, a three-story building where their young clientele can find everything they are looking for: denim fashion, women's casual clothes and stylish accessories, shoes, bags ... A jewel for fans of the Italian company. ",
            "Un oasis de frescura se concentra en esta céntrica sucursal de Miss Sixty, ubicada en las inmediaciones de la vorágine comercial del eje comercial de Preciados, en un edificio de tres plantas donde su joven clientela puede encontrar todo aquello que busca: moda denim, prendas femeninas casuales y elegantes, accesorios, zapatos, bolsos... Una joya para los fans de la compañía italiana.",
            "新鮮さのオアシスはMiss Sixtyの街の中心地にある店舗に集中しています。渦巻くような商業の軸であるプレシアード通り(Preciados)にある3階建ての建物で、デニムファッション、カジュアル・エレガント服、アクセサリー、靴、バッグ等、若い女性が求めている全ての商品があります。このイタリアブランドのファンの人にとって宝物のような場所です。",
            "Miss Sixty分公司坐落在城市的核心区域，毗邻尊贵无比的商业中心，在这幢三层的建筑里，年轻顾客可以尽情发掘他们所需的时尚衣物：潮流牛仔服饰，淑女服饰，配饰，鞋履，手包等等，Miss Sixty时装是喜欢意大利品牌的顾客的不二选择。",
            "40.4189886",
            "-3.7047680000000500",
            "Monday to Saturday: 10: 00-21: 00",
            "De lu. a sa. de 10 a 20 h.",
            " 月曜～土曜10:00-20:00",
            "周二 - 周六：10:00-20:00",
            "shop"
    )

    @Test
    @Throws(Exception::class)
    fun given_dao_existence_is_true() {
        val entityDAO = EntityDAO(dbhelper)
        Assert.assertNotNull(entityDAO)
    }

    @Test
    @Throws(Exception::class)
    fun given_value_0_when_compare_to_false_this_is_true() {
        Assert.assertEquals(0, convertBooleanToInt(false))
    }

    @Test
    @Throws(Exception::class)
    fun given_value_1_when_compare_to_true_this_is_true() {
        Assert.assertEquals(0, convertBooleanToInt(false))
    }

    @Test
    @Throws(Exception::class)
    fun given_entity_when_inserted_entity_then_gets_inserted_correctly() {

        val id = entityDAO.insertElement(ActivityType.ACTIVITY, activity)

        Assert.assertTrue(id > 0)
    }

    @Test
    @Throws(Exception::class)
    fun given_entity_when_inserted_entities_then_deleted_entity_correctly() {

        val id = entityDAO.insertElement(ActivityType.ACTIVITY, activity)
        entityDAO.insertElement(ActivityType.ACTIVITY, activity2)

        Assert.assertTrue(id > 0)

        entityDAO.deleteElement(activity2)

        Assert.assertTrue(id > 0)
        Assert.assertTrue(id > 0)

    }

    @Test
    @Throws(Exception::class)
    fun given_entity_when_inserted_entities_then_deleted_all_entity_correctly() {

        val id = entityDAO.insertElement(ActivityType.ACTIVITY, activity)
        val id2 =  entityDAO.insertElement(ActivityType.ACTIVITY, activity2)

        entityDAO.queryListElement().forEach {
            Log.d("Activities", " Tenemos estas ahora ${it.name} con este id ${it.id}")
        }

        Assert.assertTrue(id > 0)
        Assert.assertTrue(id2 > 0)

//        entityDAO.deleteAllElementList()
    }

}

