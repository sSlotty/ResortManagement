package com.example.resortmanagement.Guest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
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

/**
 * A simple [Fragment] subclass.
 * Use the [listGuestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class listGuestFragment : Fragment() {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private val viewModel by viewModel<MainViewModel>()


    private class EventItemViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        val idTxt: TextView = itemView.findViewById(R.id.idTxt)
        val nameTxt: TextView = itemView.findViewById(R.id.nameTxt)
        val telTxt: TextView = itemView.findViewById(R.id.telTxt)
    }


    private class EventListAdapter(var eventObjects:ArrayList<JSONObject>)
        : RecyclerView.Adapter<RecyclerView.ViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val viewInflater:LayoutInflater = LayoutInflater.from(parent.context)
            val entryView:View = viewInflater.inflate(R.layout.card_guest, parent, false)
            val entryViewHolder: listGuestFragment.EventItemViewHolder = listGuestFragment.EventItemViewHolder(entryView)
            return entryViewHolder
        }

        override fun getItemCount(): Int {
            return eventObjects.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val eventObj = eventObjects.get(position)
            val id = eventObj.getString("_id")
            val name = eventObj.getString("name")
            val tel = eventObj.getString("tel")

            if (holder is listGuestFragment.EventItemViewHolder)
            {
                val eventViewHolder: listGuestFragment.EventItemViewHolder = holder
                eventViewHolder.idTxt.text = id
                eventViewHolder.nameTxt.text = name
                eventViewHolder.telTxt.text = tel
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
        val v:View = inflater.inflate(R.layout.fragment_list_guest, container, false)

        mRecyclerView = v.findViewById(R.id.listGustView)
        mRecyclerView?.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView?.layoutManager = mLayoutManager

        viewModel.getAllGuests()

        viewModel.allGuest.observe(this.viewLifecycleOwner, { guest ->
            val json = Gson().toJson(guest)
            parseJsonEvent(json)
        })
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnAddGuest = view.findViewById<ImageButton>(R.id.btnAddGuest)

        btnAddGuest.setOnClickListener {

            (context as GuestActivity).changeFragment(addGuestFragment.newInstance())
            Toast.makeText(this.context,"Test Add Guest",Toast.LENGTH_LONG).show()
        }
    }

    companion object {

        fun newInstance(): listGuestFragment {
            return listGuestFragment()
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
        mAdapter= listGuestFragment.EventListAdapter(eventObjects)
        mRecyclerView?.adapter = mAdapter
    }
}