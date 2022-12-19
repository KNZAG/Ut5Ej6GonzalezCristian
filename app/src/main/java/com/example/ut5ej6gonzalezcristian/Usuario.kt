package com.example.ut5ej6gonzalezcristian

/*import android.os.Parcelable
import kotlinx.parcelize.Parcelize*/

//@Parcelize
data class Usuario(val login: String, val contrasena: String = "", val tipoUsuario: Int = 0) /*: Parcelable*/ {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        if (login != other.login) return false

        return true
    }

    override fun hashCode(): Int {
        return login.hashCode()
    }

}