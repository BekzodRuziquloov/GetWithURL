package space.beka.getwithurl.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.beka.getwithurl.databinding.ItemRvBinding
import space.beka.getwithurl.models.MyTodoGetResponse

class MyGetdoAdapter(var list: List<MyTodoGetResponse>) : RecyclerView.Adapter<MyGetdoAdapter.Vh>() {
    inner  class Vh(var itemRvBinding: ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(myTodoGetResponse: MyTodoGetResponse, position: Int) {
itemRvBinding.tvName.text = myTodoGetResponse.sarlavha
            itemRvBinding.tvMatn.text = myTodoGetResponse.matn
            itemRvBinding.tvDate.text = myTodoGetResponse.oxirgi_muddat
            itemRvBinding.tvHolat.text = myTodoGetResponse.holat
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position] ,position)
    }

    override fun getItemCount(): Int =list.size



}