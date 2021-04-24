package com.example.resortmanagement.Employee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.resortmanagement.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

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

//    private var mRecyclerView:RecyclerView? = null
//    private var mAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
//    private var mLayoutManager:RecyclerView.LayoutManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v:View = inflater.inflate(R.layout.fragment_list_employee, container, false)

        GlobalScope.launch(Dispatchers.Main) {
            val result = httpGetEvents("http://localhost:5000/getuser")
            Toast.makeText(activity, result, Toast.LENGTH_LONG).show()
        }

        return v
    }

    companion object {

        fun newInstance():ListEmployee{
            return ListEmployee()
        }
    }

    private suspend fun  httpGetEvents(eventUrlStr:String):String? = withContext(Dispatchers.IO) {
        val eventUrl:URL = URL(eventUrlStr)
        val conn:HttpURLConnection = eventUrl.openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.connect()

        val inStream:InputStream = conn.inputStream
        val inStreamReader:InputStreamReader = InputStreamReader(inStream,"UTF-8")
        val buffReader:BufferedReader = BufferedReader(inStreamReader)

        var  sb:StringBuilder = StringBuilder()
        var line_read:String? = buffReader.readLine()
        while (line_read!=null) {
            sb.append(line_read)
            line_read = buffReader.readLine()
        }
        inStream.close()
        sb.toString()
    }



//    private class EventItemViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
//    {
//        val NameTxt:TextView = itemView.findViewById(R.id.NameTxt)
//    }

}