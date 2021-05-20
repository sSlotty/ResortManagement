package com.example.resortmanagement.Checkout

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resortmanagement.Booking.listRoom
import com.example.resortmanagement.Employee.ListEmployee
import com.example.resortmanagement.MainViewModel
import com.example.resortmanagement.R
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class listTransactionFragment : Fragment() {

    private var mRecyclerView:RecyclerView? = null
    private var mAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    private var mLayoutManager:RecyclerView.LayoutManager? = null
    private val viewModel by viewModel<MainViewModel>()
    lateinit var callback :((id: String) -> Unit)

    private class EventItemViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        val id_transactionTxt: TextView = itemView.findViewById(R.id.id_transactionTxt)
        val check_inTxt: TextView = itemView.findViewById(R.id.check_inTxt)
        val guest_idTxt: TextView = itemView.findViewById(R.id.guest_idTxt)
        val statusTxt : TextView = itemView.findViewById(R.id.status_Txt)
        val cardView:CardView = itemView.findViewById(R.id.cardView)
    }

    private class EventListAdapter(var eventObjects:ArrayList<JSONObject>,var callback:((id:String)->Unit))
        : RecyclerView.Adapter<RecyclerView.ViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val viewInflater:LayoutInflater = LayoutInflater.from(parent.context)
            val entryView:View = viewInflater.inflate(R.layout.card_list_transaction, parent, false)
            val entryViewHolder:EventItemViewHolder = EventItemViewHolder(entryView)
            return entryViewHolder
        }

        override fun getItemCount(): Int {
            return eventObjects.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val eventObj = eventObjects.get(position)
            val id = eventObj.getString("_id")
            val check_in = eventObj.getString("check_in")
            val guestID = eventObj.getString("guestID")
            val status = eventObj.getString("status")

            if (holder is EventItemViewHolder)
            {
                val eventViewHolder:EventItemViewHolder = holder
                eventViewHolder.id_transactionTxt.text = id
                eventViewHolder.check_inTxt.text = check_in
                eventViewHolder.guest_idTxt.text = guestID
                if(status.equals("True")){
                    eventViewHolder.statusTxt.text = "Checkout"
                    eventViewHolder.statusTxt.setBackgroundColor(Color.parseColor("#10B981"))
                    eventViewHolder.statusTxt.setTextColor(Color.WHITE)

                }else{
                    eventViewHolder.statusTxt.text = "No check out"
                    eventViewHolder.statusTxt.setBackgroundColor(Color.parseColor("#EF4444"))
                    eventViewHolder.statusTxt.setTextColor(Color.WHITE)
                }
                eventViewHolder.cardView.setOnClickListener{
                    callback.invoke(id)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v:View = inflater.inflate(R.layout.fragment_list_transaction, container, false)
//       Register RecyclerView Adapter
        mRecyclerView = v.findViewById(R.id.TransactionRecyclerView)
        mRecyclerView?.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView?.layoutManager = mLayoutManager

        viewModel.getBooking{
            if(it){
                val json = Gson().toJson(viewModel.transaction.value)
                parseJsonEvent(json)
            }else{
                Toast.makeText(this.context,"Error", Toast.LENGTH_LONG).show()
            }
        }
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        fun newInstance(): listTransactionFragment {
            return listTransactionFragment()
        }
    }
    private var eventObjects = ArrayList<JSONObject>()
    private fun parseJsonEvent(jsonStr:String)
    {
        eventObjects.clear()

        val eventArray = JSONArray(jsonStr)
        for (i in 0 until eventArray.length())
        {
            val eventObj = eventArray.getJSONObject(i)
            eventObjects.add(eventObj)
        }
        mAdapter = EventListAdapter(eventObjects){
            callback.invoke(it)
        }
        mRecyclerView?.adapter = mAdapter
    }

}

