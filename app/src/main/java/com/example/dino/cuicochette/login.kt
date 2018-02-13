package com.example.dino.cuicochette

import android.util.Log
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by bapti on 12/02/2018.
 */
class login {

    companion object {
        fun checkCo(id_t: String, pwd_t: String): Boolean {
            var result: String? = ""
            var ok: Boolean = false
            val localLoginString = "http://10.0.2.2:3306/connexion?login=chef&mdp=12345"

            try {
                val jsonResponse = JSONObject(localLoginString)
                val jsonMainNode = jsonResponse.optJSONArray("utilisateur")


                val jsonChildNode = jsonMainNode.getJSONObject(0)
                val name = jsonChildNode.optString("login")
                val number = jsonChildNode.optString("mdp")
                Log.d("login", name + "  " + number)
                Log.d("login", id_t + "  " + pwd_t)

                if (name == id_t && number == pwd_t) {
                    ok = true
                }
            } catch (e: JSONException) {
                Log.d("error", e.toString())
            }
            return ok!!
        }


        fun getRole(): String? {
            var result = ""
            val localLoginString = "http://10.0.2.2:3306/connexion?login=chef&mdp=12345"
            val jsonResponse = JSONObject(localLoginString)
            val jsonMainNode = jsonResponse.optJSONArray("utilisateur")


            val jsonChildNode = jsonMainNode.getJSONObject(0)

            val role = jsonChildNode.optString("type")

            if (role.equals("chef")) result = "chef"
            if (role.equals("client")) result = "client"

            return result
        }

        fun getResto() : Int?{

            val localLoginString = "http://10.0.2.2:3306/connexion?login=chef&mdp=12345"
            val jsonResponse = JSONObject(localLoginString)
            val jsonMainNode = jsonResponse.optJSONArray("utilisateur")


            val jsonChildNode = jsonMainNode.getJSONObject(0)

            val role = jsonChildNode.optInt("restaurant")


            return role
        }

        fun getTable() : Int?{

            val localLoginString = "http://10.0.2.2:3306/connexion?login=chef&mdp=12345"
            val jsonResponse = JSONObject(localLoginString)
            val jsonMainNode = jsonResponse.optJSONArray("utilisateur")


            val jsonChildNode = jsonMainNode.getJSONObject(0)

            val table = jsonChildNode.optInt("numeroTable")


            return table
        }
    }
}