package space.beka.getwithurl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import retrofit2.Call
import retrofit2.Response
import space.beka.getwithurl.adapters.MyGetdoAdapter
import space.beka.getwithurl.databinding.ActivityMainBinding
import space.beka.getwithurl.databinding.ItemDialogBinding
import space.beka.getwithurl.models.MyTodoGetResponse
import space.beka.getwithurl.models.MyTodoPostRequest
import space.beka.getwithurl.retrofit.ApiClient
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myGetdoAdapter: MyGetdoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        postData()
        return super.onOptionsItemSelected(item)
    }

    private fun getData() {
        ApiClient.getRetrofitService().getAllTodo()
            .enqueue(object : retrofit2.Callback<List<MyTodoGetResponse>> {
                override fun onResponse(
                    call: Call<List<MyTodoGetResponse>>,
                    response: Response<List<MyTodoGetResponse>>
                ) {
                    if (response.isSuccessful) {
                        myGetdoAdapter = MyGetdoAdapter(response.body()!!)
                        binding.rv.adapter = myGetdoAdapter
                    }
                }

                override fun onFailure(call: Call<List<MyTodoGetResponse>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Internet bilan muammo", Toast.LENGTH_SHORT)
                        .show()
                }
            })

    }
    private fun postData() {
        val dialog = AlertDialog.Builder(this).create()
        val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
        dialog.setView(itemDialogBinding.root)

        itemDialogBinding.apply {
            btnSave.setOnClickListener {
                val myTodoPostRequest = MyTodoPostRequest(
                    spinnerHolat.selectedItem.toString(),
                    edtMatn.text.toString(),
                    edtMuddat.text.toString(),
                    edtSarlavha.text.toString()
                )
                ApiClient.getRetrofitService().addToDo(myTodoPostRequest)
                    .enqueue(object : retrofit2.Callback<MyTodoGetResponse> {
                        override fun onResponse(
                            call: Call<MyTodoGetResponse>,
                            response: Response<MyTodoGetResponse>
                        ) {
                            if (response.isSuccessful) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "${response.body()?.sarlavha} saqlandi",
                                    Toast.LENGTH_SHORT
                                ).show()
                                dialog.cancel()
                                getData()
                            }
                        }

                        override fun onFailure(call: Call<MyTodoGetResponse>, t: Throwable) {
                            Toast.makeText(
                                this@MainActivity,
                                "Internet bilan muammo saqlanmadi",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            }
        }
        dialog.show()
    }

}