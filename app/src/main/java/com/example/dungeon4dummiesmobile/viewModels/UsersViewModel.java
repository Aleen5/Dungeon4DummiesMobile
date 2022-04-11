package com.example.dungeon4dummiesmobile.viewModels;

import android.content.Context;
import android.widget.Toast;
import androidx.activity.viewModels;
import androidx.compose.runtime.Composable;
import androidx.compose.runtime.getValue;
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
//import com.example.dungeon4dummiesmobile.AppNavigation;
import kotlinx.coroutines.launch
import com.example.dungeon4dummiesmobile.models.UsersModel;
import com.example.dungeon4dummiesmobile.services.ApiServices;
import java.lang.Exception

class UsersViewModel : ViewModel(){
        var employeeComprobator by mutableStateOf(false)
        var employeeModelListResponse : List<EmployeeModel> by mutableStateOf(listOf())
        var employeeModel : EmployeeModel by mutableStateOf(EmployeeModel("uwu", "uwu", "uwu", "uwu", "uwu", "uwu", "uwu", "uwu"))
private var errorMessage : String by mutableStateOf("")
        var logged by mutableStateOf(false)
        var loginFailures by mutableStateOf(3)

        fun getEmployeeList() {
        viewModelScope.launch{
        val apiServices = ApiServices.getInstance()
        try {
        val employeeList = apiServices.getEmployees()
        employeeModelListResponse = employeeList
        }
        catch (e : Exception) {
        errorMessage = e.message.toString()
        }
        }
        }

        fun get1Employee(dni: String, pwd: String, context: Context) {
        viewModelScope.launch {
        val apiServices = ApiServices.getInstance()
        try {
        if(dni == ""){
        loginFailures--
        Toast.makeText(context,
        "El dni no puede estar vacío, intentos restantes: $loginFailures", Toast.LENGTH_SHORT).show()
        }
        else if(pwd == ""){
        loginFailures--
        Toast.makeText(context,
        "La contraseña no puede estar vacía, intentos restantes: $loginFailures", Toast.LENGTH_SHORT).show()
        }
        else{
        val employee1 = apiServices.getEmployee(dni)
        if(employee1.isSuccessful){
        employeeModel = employee1.body()!!
        if(employeeModel.dni == dni && employeeModel.contraseña == pwd){
        employeeComprobator = true
        }
        else{
        loginFailures--
        Toast.makeText(context, "Contraseña incorrecta, intentos restantes: $loginFailures", Toast.LENGTH_SHORT).show()
        }
        }else{
        Toast.makeText(context, "Error al conectar con la base de datos.", Toast.LENGTH_LONG).show()
        }
        }
        }
        catch (e : Exception) {
        errorMessage = e.message.toString()
        loginFailures--
        Toast.makeText(context, "El dni no existe o es incorrecto, intentos restantes: $loginFailures", Toast.LENGTH_LONG).show()
        }

            /*
            Toast.makeText(context, employeeComprobator.toString(), Toast.LENGTH_LONG).show()
            Toast.makeText(context, dni, Toast.LENGTH_LONG).show()
            Toast.makeText(context, pwd, Toast.LENGTH_LONG).show()*/
        }
        }
    /*
    private fun checkLogin(employee: EmployeeModel, dni: String, pwd: String, context: Context): Boolean {
        Toast.makeText(context, "Boton pulsado", Toast.LENGTH_SHORT).show()
        if(employee.dni == dni && employee.contraseña == pwd){
            return true
        }
        return false
    }*/
        }