package com.example.resortmanagement.Room

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.resortmanagement.Employee.ListEmployee
import com.example.resortmanagement.MainViewModel
import com.example.resortmanagement.R
import com.example.resortmanagement.model.Rooms
import com.example.resortmanagement.model.RoomsItem
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.coroutineContext
import kotlin.reflect.typeOf

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
        val btnAdd = itemView.findViewById<ImageButton>(R.id.btnAddRoom)
    }

    private class EvenetListAdapter(var eventObjects:Rooms) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            TODO("Not yet implemented")

        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            TODO("Not yet implemented")
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


        viewModel.getAllRoom()
        viewModel.allRoom.observe(this.viewLifecycleOwner, Observer { rooms ->
            mAdapter = EvenetListAdapter(rooms)
            val json = Gson().toJson(rooms)
            Toast.makeText(this.context, "Response $json", Toast.LENGTH_LONG).show()
        })

//        btnAdd.setOnClickListener { v ->
//            Toast.makeText(this.context,"Test BTN",Toast.LENGTH_LONG)
//        }

        // Inflate the layout for this fragment
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnAdd = view.findViewById<ImageButton>(R.id.btnAddRoom)
        btnAdd.setOnClickListener {
            Toast.makeText(this.context,"Test btn",Toast.LENGTH_LONG).show()
        }
    }

    companion object {

        fun newInstance():listRoomManagement {
            return listRoomManagement()
        }
    }


}