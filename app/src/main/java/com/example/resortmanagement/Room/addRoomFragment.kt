package com.example.resortmanagement.Room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.resortmanagement.MainViewModel
import com.example.resortmanagement.R
import kotlinx.android.synthetic.main.card_list_room.*
import kotlinx.android.synthetic.main.fragment_add_guest.*
import kotlinx.android.synthetic.main.fragment_add_room.*
import kotlinx.android.synthetic.main.fragment_add_room.btnSaveRoom
import kotlinx.android.synthetic.main.fragment_add_room.roomPerson
import kotlinx.android.synthetic.main.fragment_add_room.roomId
import org.koin.androidx.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [addRoomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class addRoomFragment : Fragment() {
    private val viewModel by viewModel<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v:View = inflater.inflate(R.layout.fragment_add_room, container, false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        btnSaveRoom.setOnClickListener {

            if (roomId.text.isNullOrEmpty() or roomPerson.text.isNullOrEmpty() or inputRoomType.text.isNullOrEmpty() or switchStatus.showText or roomPrice.text.isNullOrEmpty()){
                Toast.makeText(this.context,"Some field is null or empty",Toast.LENGTH_LONG).show()
            }else{

                var check = if(switchStatus.isChecked){
                    "True"
                }else{
                    "False"
                }

                val data = hashMapOf<String,Any>("roomID" to roomId.text.toString(),"roomType" to inputRoomType.text.toString(),"person" to roomPerson.text.toString().toInt() ,"price" to roomPrice.text.toString().toDouble(),"room_status" to check)
                saveRoom(data)
            }
        }
    }

    private fun saveRoom(data:HashMap<String,Any>){
        val load =  SweetAlertDialog(this.context, SweetAlertDialog.PROGRESS_TYPE)
            .showCancelButton(false)
            .setTitleText("loading...")

        val success = SweetAlertDialog(this.context, SweetAlertDialog.SUCCESS_TYPE)
            .showCancelButton(false)

        val error = SweetAlertDialog(this.context, SweetAlertDialog.ERROR_TYPE)
            .showCancelButton(false)
        load.show()

        viewModel.addRoom(data){status ->
            if(status){
                load.hide()
                success.titleText = "Success to create"
                success.show()
            }else{
                load.hide()
                error.titleText = "Fail to add Room"
                error.show()
            }
        }
    }

    companion object {

        fun newInstance(): addRoomFragment{
            return  addRoomFragment()
        }
    }
}