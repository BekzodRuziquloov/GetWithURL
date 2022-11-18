package space.beka.getwithurl.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import space.beka.getwithurl.models.MyTodoGetResponse
import space.beka.getwithurl.models.MyTodoPostRequest

interface RetrofitServise {
    @GET("plan")
    fun getAllTodo():retrofit2.Call<List<MyTodoGetResponse>>
@POST("plan/")
fun addToDo( @Body myTodoPostRequest: MyTodoPostRequest):Call<MyTodoGetResponse>

}

