package com.example.resortmanagement.Booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resortmanagement.MainViewModel
import com.example.resortmanagement.R
import com.example.resortmanagement.Room.listRoomManagement
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [listRoom.newInstance] factory method to
 * create an instance of this fragment.
 */
class listRoom : Fragment() {

    private val viewModel by viewModel<MainViewModel>()
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

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
            val entryViewHolder: listRoom.EventItemViewHolder = listRoom.EventItemViewHolder(entryView)
            return entryViewHolder

        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val eventObj = eventObjects.get(position)
            val roomid = eventObj.getString("_id")
            val roomtype = eventObj.getString("roomType")
            val person = eventObj.getString("person")
            val price = eventObj.getString("price")
            val room_status = eventObj.getString("room_status")


            if (holder is listRoom.EventItemViewHolder)
            {
                val eventViewHolder: listRoom.EventItemViewHolder = holder
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

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstantState: Bundle?
    ): View?  {
        val v:View = inflater.inflate(R.layout.fragment_list_room, container, false)
        mRecyclerView = v.findViewById(R.id.bookingroomRecyclerView)
        mRecyclerView?.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView?.layoutManager = mLayoutManager


        viewModel.getRoomStatus(){ status ->
            if (status){
                val json = Gson().toJson(viewModel.roomStatus.value)
                parseJsonEvent(json)
            }else{
                Toast.makeText(this.context,"Error",Toast.LENGTH_LONG).show()
            }
        }

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val btnChangeFragment = view.findViewById<ImageButton>(R.id.btnChangeFragment)
//        btnChangeFragment.setOnClickListener {
//            (context as Booking).changeFragment(aboutBooking.newInstance())
//        }
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
        mAdapter= listRoom.EvenetListAdapter(eventObjects)
        mRecyclerView?.adapter = mAdapter
    }

    companion object {
        fun newInstance(): listRoom {
            return listRoom()
        }
    }
}