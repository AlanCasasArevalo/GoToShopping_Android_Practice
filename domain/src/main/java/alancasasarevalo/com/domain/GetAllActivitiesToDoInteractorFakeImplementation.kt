package alancasasarevalo.com.domain

import alancasasarevalo.com.activitytype.ActivityType
import alancasasarevalo.com.domain.model.ActivitiesToDo
import alancasasarevalo.com.domain.model.ActivityToDo

class GetAllActivitiesToDoInteractorFakeImplementation : GetAllActivitiesToDoInteractor {
    override fun execute(successCompletion: SuccessCompletion<ActivitiesToDo>, error: ErrorCompletion) {
        var allOk = true

        if (allOk){
            val activitiesToDo = createFakeListOfActivies()
            successCompletion.successCompletion(activitiesToDo)
        }else{
            error.errorCompletion("Error al acceder al repositorio")
        }

    }

    override fun executeForType(type: ActivityType, successCompletion: SuccessCompletion<ActivitiesToDo>, error: ErrorCompletion) {
        var allOk = true

        if (allOk){
            val activitiesToDo = createFakeListOfActivies()
            successCompletion.successCompletion(activitiesToDo)
        }else{
            error.errorCompletion("Error al acceder al repositorio")
        }
    }

    private fun createFakeListOfActivies(): ActivitiesToDo {
        val list = ArrayList<ActivityToDo>()

        for (i in 0..50){
            val activityToDo = ActivityToDo(
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
                    "De Lunes a Sábado: 10:00-21:00",
                    "",
                    "周二 - 周六：10:00-21:00",
                    "Shop"
            )

            list.add(activityToDo)
        }

        val activitiesToDo = ActivitiesToDo(list)

        return activitiesToDo
    }

}