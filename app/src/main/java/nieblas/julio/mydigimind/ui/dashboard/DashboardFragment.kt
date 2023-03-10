package nieblas.julio.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import nieblas.julio.mydigimind.R
import nieblas.julio.mydigimind.databinding.FragmentDashboardBinding
import nieblas.julio.mydigimind.ui.Task
import nieblas.julio.mydigimind.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val btn_time: Button = root.findViewById(R.id.btn_time)
        btn_time.setOnClickListener{
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{ timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btn_time.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), true).show()
        }

       val btn_save = root.findViewById(R.id.btn_save) as Button
       val et_titulo = root.findViewById(R.id.et_task) as EditText
       val checkMonday = root.findViewById(R.id.checkMonday) as CheckBox
       val checkTuesday = root.findViewById(R.id.checkTuesday) as CheckBox
       val checkWednesday = root.findViewById(R.id.checkWednesday) as CheckBox
       val checkThursday = root.findViewById(R.id.checkThursday) as CheckBox
       val checkFriday = root.findViewById(R.id.checkFriday) as CheckBox
       val checkSaturday = root.findViewById(R.id.checkSaturday) as CheckBox
       val checkSunday = root.findViewById(R.id.checkSunday) as CheckBox

        btn_save.setOnClickListener{

            var days = ArrayList<String>()
            var time = btn_time.text.toString()
            var title = et_titulo.text.toString()

            if (checkMonday.isChecked)
                days.add("Monday")
            if (checkTuesday.isChecked)
                days.add("Tuesday")
            if (checkWednesday.isChecked)
                days.add("Wednesday")
            if (checkThursday.isChecked)
                days.add("Thurday")
            if (checkFriday.isChecked)
                days.add("Friday")
            if (checkSaturday.isChecked)
                days.add("Saturday")
            if (checkSunday.isChecked)
                days.add("Sunday")

            var task = Task(title,days,time)

            HomeFragment.tasks.add(task)

            Toast.makeText(root.context, "new task added", Toast.LENGTH_SHORT).show()
        }

        return root

    }
    
}