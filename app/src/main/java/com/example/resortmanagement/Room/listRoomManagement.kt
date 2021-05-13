package com.example.resortmanagement.Room

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

/**
 * A simple [Fragment] subclass.
 * Use the [listRoomManagement.newInstance] factory method to
 * create an instance of this fragment.
 */
class listRoomManagement : Fragment() {

    private val viewModel by viewModel<MainViewModel>()
    private var mRecyclerView:RecyclerView? = null
    private var mAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    private var mLayoutManager:RecyclerView.LayoutManager? = null


    private class EventItemViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val roomidTxt: TextView = itemView.findViewById(R.id.roomidTxt)
        val roomtypeTxt: TextView = itemView.findViewById(R.id.roomtypeTxt)
        val personTxt: TextView = itemView.findViewById(R.id.personTxt)
        val priceTxt: TextView = itemView.findViewById(R.id.priceTxt)
        val statusTxt: TextView = itemView.findViewById(R.id.statusTxt)
    }

    private class EvenetListAdapter(var eventObjects:ArrayList<JSONObject>)
        : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val viewInflater:LayoutInflater = LayoutInflater.from(parent.context)
            val entryView:View = viewInflater.inflate(R.layout.card_list_room, parent, false)
            val entryViewHolder: listRoomManagement.EventItemViewHolder = listRoomManagement.EventItemViewHolder(entryView)
            return entryViewHolder

        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val eventObj = eventObjects.get(position)
            val roomid = eventObj.getString("_id")
            val roomtype = eventObj.getString("roomType")
            val person = eventObj.getString("person")
            val price = eventObj.getString("price")
            val room_status = eventObj.getString("room_status")


            if (holder is listRoomManagement.EventItemViewHolder)
            {
                val eventViewHolder: listRoomManagement.EventItemViewHolder = holder
                eventViewHolder.roomidTxt.text = roomid
                eventViewHolder.roomtypeTxt.text = roomtype
                eventViewHolder.personTxt.text = person
                eventViewHolder.priceTxt.text = price
                eventViewHolder.statusTxt.text = room_status

            }
        }

        override fun getItemCount(): Int {
            return eventObjects.size
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val v:View = inflater.inflate(R.layout.fragment_list_room_management, container, false)
        mRecyclerView = v.findViewById(R.id.roomRecyclerView)
        mRecyclerView?.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView?.layoutManager = mLayoutManager


        viewModel.getAllRoom(){ status ->
            if (status){
                val json = Gson().toJson(viewModel.allRoom.value)
                parseJsonEvent(json)
            }else{
                Toast.makeText(this.context,"Error",Toast.LENGTH_LONG).show()
            }
        }
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnAdd = view.findViewById<ImageButton>(R.id.btnAddRoom)
        btnAdd.setOnClickListener {
            Toast.makeText(this.context,"Test btn",Toast.LENGTH_LONG).show()
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
        mAdapter= listRoomManagement.EvenetListAdapter(eventObjects)
        mRecyclerView?.adapter = mAdapter
    }

    companion object {

        fun newInstance():listRoomManagement {
            return listRoomManagement()
        }
    }


}