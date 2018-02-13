package com.example.dino.cuicochette

import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.net.URL

/**
 * Created by bapti on 12/02/2018.
 */
class login {

    companion object {
        fun checkCo(id_t: String, pwd_t: String): String?  {

            var role : String? = ""
            val localLoginString = "http://10.0.2.2:3306/connexion?login="+id_t+"&mdp="+pwd_t;

            try {
                val result = URL(localLoginString).readText()
                val jsonObject = JSONObject(result)
                val type = jsonObject.getString("type")
                val restaurant  = jsonObject.getInt("restaurant")


                role = getRole(type, restaurant)

            } catch (e: JSONException) {
                Log.d("error", e.toString())
            }
            return role
        }


        fun getRole(type: String , restaurant : Int): String? {
            var result = ""



            if (type.equals("chef")) result = "chef"
            if (type.equals("table")) result = "client"

            return result
        }

        fun getTable() : Int?{

            val localLoginString = "http://10.0.2.2:3306/connexion?login=chef&mdp=12345"
            val jsonResponse = JSONObject(localLoginString)
            val jsonMainNode = jsonResponse.optJSONArray("utilisateur")


            val jsonChildNode = jsonMainNode.getJSONObject(0)

            val table = jsonChildNode.optInt("numeroTable")


            return table
        }


        fun getIdResto(id_t: String, pwd_t: String): Int?  {

            var restaurant : Int? = -1
            val localLoginString = "http://10.0.2.2:3306/connexion?login="+id_t+"&mdp="+pwd_t;

            try {
                val result = URL(localLoginString).readText()
                val jsonObject = JSONObject(result)
                restaurant  = jsonObject.getInt("restaurant")




            } catch (e: JSONException) {
                Log.d("error", e.toString())
            }
            return restaurant
        }
    }
}