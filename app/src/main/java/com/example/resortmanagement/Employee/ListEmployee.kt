package com.example.resortmanagement.Employee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
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
 * Use the [ListEmployee.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListEmployee : Fragment() {

    private var mRecyclerView:RecyclerView? = null
    private var mAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    private var mLayoutManager:RecyclerView.LayoutManager? = null
    private val viewModel by viewModel<MainViewModel>()

    private class EventItemViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        val idTxt:TextView = itemView.findViewById(R.id.idTxt)
        val nameTxt:TextView = itemView.findViewById(R.id.nameTxt)

    }

    private class EventListAdapter(var eventObjects:ArrayList<JSONObject>)
        : RecyclerView.Adapter<RecyclerView.ViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val viewInflater:LayoutInflater = LayoutInflater.from(parent.context)
            val entryView:View = viewInflater.inflate(R.layout.card_list_emp, parent, false)
            val entryViewHolder:EventItemViewHolder = EventItemViewHolder(entryView)
            return entryViewHolder
        }

        override fun getItemCount(): Int {
            return eventObjects.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val eventObj = eventObjects.get(position)
            val id = eventObj.getString("_id")
            val name = eventObj.getString("name")


            if (holder is EventItemViewHolder)
            {
                val eventViewHolder:EventItemViewHolder = holder
                eventViewHolder.idTxt.text = id
                eventViewHolder.nameTxt.text = name

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
        val v:View = inflater.inflate(R.layout.fragment_list_employee, container, false)
//       Register RecyclerView Adapter
        mRecyclerView = v.findViewById(R.id.empRecyclerView)
        mRecyclerView?.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView?.layoutManager = mLayoutManager

        viewModel.getUser{status ->
            if (status){
                val json = Gson().toJson(viewModel.user.value)
                parseJsonEvent(json)
            }
        }

        val btnAddEmp = v.findViewById<ImageButton>(R.id.btnAddEmp)
        btnAddEmp.setOnClickListener {
            (context as Employee).changeFragment(addEmpFragment.newInstance())
        }

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }


    companion object {

        fun newInstance():ListEmployee{
            return ListEmployee()
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
        mAdapter= EventListAdapter(eventObjects)
        mRecyclerView?.adapter = mAdapter
    }

}