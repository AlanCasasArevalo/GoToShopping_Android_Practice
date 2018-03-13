package alancasasarevalo.com.repository.database

import alancasasarevalo.com.activitytype.ActivityType
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

internal class EntityDAO (dbHelper: DBHelper) : DAOPersistable<EntityModel> {

    private val dbReadOnlyConnection: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase

    override fun queryWithId(id: Long): EntityModel {
        val cursor = queryCursor(id)
        cursor.moveToFirst()
        return entityFromCursor(cursor)!!
    }

    override fun queryListElement(): List<EntityModel> {
        val queryResults = arrayListOf<EntityModel>()

        val cursor = dbReadOnlyConnection.query(
                DBConstants.TABLE_ENTITY,
                DBConstants.ALL_COLUMNS,
                null,
                null,
                "",
                "",
                DBConstants.KEY_ENTITY_DATABASE_ID
        )
        while (cursor.moveToNext()){
            val entity = entityFromCursor(cursor)
            queryResults.add(entity!!)
        }

        return queryResults
    }

    override fun queryType(type: ActivityType): List<EntityModel> {
        val queryResult = ArrayList<EntityModel>()

        val cursor = dbReadOnlyConnection.query(DBConstants.TABLE_ENTITY,
                DBConstants.ALL_COLUMNS,
                DBConstants.KEY_ENTITY_TYPE + " = ?",
                arrayOf(type.type),
                "",
                "",
                DBConstants.KEY_ENTITY_NAME)

        while (cursor.moveToNext()){
            val se = entityFromCursor(cursor)
            queryResult.add(se!!)
        }

        return queryResult

    }

    override fun queryCursor(id: Long): Cursor {
        val cursor = dbReadOnlyConnection.query(
                DBConstants.TABLE_ENTITY,
                DBConstants.ALL_COLUMNS,
                DBConstants.KEY_ENTITY_DATABASE_ID + " = ?",
                arrayOf(id.toString()),
                "",
                "",
                DBConstants.KEY_ENTITY_DATABASE_ID
        )
        return cursor
    }

    override fun insertElement(type: ActivityType, element: EntityModel): Long {
        var id: Long = 0
        id = dbReadOnlyConnection.insert(DBConstants.TABLE_ENTITY, null, contentValues(type.type, element))
        return id
    }

    override fun updateElementById(id: Long, element: EntityModel): Long {
        val numberOfRecordsUpdate = dbReadWriteConnection.update(
                DBConstants.TABLE_ENTITY,
                contentValues(element.type.toString(), element),
                DBConstants.KEY_ENTITY_DATABASE_ID + " = ?",
                arrayOf(id.toString())
        ).toLong()

        return numberOfRecordsUpdate
    }

    override fun deleteElement(element: EntityModel): Long {
        if (element.databaseId < 1) {
            return 0
        }

        return deleteElementById(element.databaseId)
    }

    override fun deleteElementById(id: Long): Long {
        return dbReadOnlyConnection.delete(DBConstants.TABLE_ENTITY,
                DBConstants.KEY_ENTITY_DATABASE_ID + " = ?",
                arrayOf(id.toString())
        ).toLong()
    }

    override fun deleteAllElementList(): Boolean {
        return dbReadWriteConnection.delete(DBConstants.TABLE_ENTITY,
                null,
                null).toLong() >= 0
    }

    fun contentValues(type: String, entityModel: EntityModel): ContentValues {
        val content = ContentValues()

        content.put(DBConstants.KEY_ENTITY_ID_JSON, entityModel.id)
        content.put(DBConstants.KEY_ENTITY_NAME, entityModel.name)

        content.put(DBConstants.KEY_ENTITY_IMAGE_URL, entityModel.img)
        content.put(DBConstants.KEY_ENTITY_LOGO_IMAGE_URL, entityModel.logo)
        content.put(DBConstants.KEY_ENTITY_ADDRESS, entityModel.address)

        content.put(DBConstants.KEY_ENTITY_URL, entityModel.url)
        content.put(DBConstants.KEY_ENTITY_EMAIL, entityModel.email)
        content.put(DBConstants.KEY_ENTITY_TELEPHONE, entityModel.telephone)

        content.put(DBConstants.KEY_ENTITY_DESCRIPTION_EN, entityModel.description_en)
        content.put(DBConstants.KEY_ENTITY_DESCRIPTION_ES, entityModel.description_es)
        content.put(DBConstants.KEY_ENTITY_DESCRIPTION_CN, entityModel.description_jp)
        content.put(DBConstants.KEY_ENTITY_DESCRIPTION_JP, entityModel.description_cn)

        content.put(DBConstants.KEY_ENTITY_LATITUDE, entityModel.latitude)
        content.put(DBConstants.KEY_ENTITY_LONGITUDE, entityModel.longitude)

        content.put(DBConstants.KEY_ENTITY_OPENING_HOURS_EN, entityModel.openingHoursEn)
        content.put(DBConstants.KEY_ENTITY_OPENING_HOURS_ES, entityModel.openingHoursEs)
        content.put(DBConstants.KEY_ENTITY_OPENING_HOURS_JP, entityModel.openingHoursJp)
        content.put(DBConstants.KEY_ENTITY_OPENING_HOURS_CN, entityModel.openingHoursCn)

        content.put(DBConstants.KEY_ENTITY_TYPE, type)

        return content
    }

    fun entityFromCursor(cursor: Cursor): EntityModel? {
        if (cursor.isAfterLast || cursor.isBeforeFirst) {
            return null
        }

        return EntityModel(
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_ENTITY_DATABASE_ID)),
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_ENTITY_ID_JSON)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_NAME)),

                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_LOGO_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_ADDRESS)),

                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_EMAIL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_TELEPHONE)),

                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_DESCRIPTION_EN)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_DESCRIPTION_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_DESCRIPTION_JP)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_DESCRIPTION_CN)),

                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_LATITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_LONGITUDE)),

                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_OPENING_HOURS_EN)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_OPENING_HOURS_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_OPENING_HOURS_JP)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_OPENING_HOURS_CN)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_TYPE))
        )

    }

}

