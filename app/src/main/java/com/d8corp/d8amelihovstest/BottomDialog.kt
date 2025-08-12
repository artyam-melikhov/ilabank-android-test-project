package com.d8corp.d8amelihovstest

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomDialog(val items: List<ListItem>, val position: Int) : BottomSheetDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val view = View.inflate(context, R.layout.bottom_dialog, null)
        dialog.setContentView(view)

        view.setOnClickListener { dismiss() }

        view.findViewById<TextView>(R.id.current_screen).setText("List " + position)

        val occurencies = computeOccurencies(items)
        view.findViewById<TextView>(R.id.char_1).setText("${occurencies[0].char}: ${occurencies[0].occurency}")
        view.findViewById<TextView>(R.id.char_2).setText("${occurencies[1].char}: ${occurencies[1].occurency}")
        view.findViewById<TextView>(R.id.char_3).setText("${occurencies[2].char}: ${occurencies[2].occurency}")

        return dialog
    }

    class Occurency(val char: Char, val occurency: Int)

    fun computeOccurencies(items: List<ListItem>) : List<Occurency> {
        val occurrencesMap = mutableMapOf<Char, Int>()
        items.map { it.title }
            .forEach {
                for (c in it) {
                    occurrencesMap.putIfAbsent(c, 0)
                    occurrencesMap[c] = occurrencesMap[c]!! + 1
                }
            }

        return occurrencesMap.map { Occurency(it.key, it.value) }.toList().sortedBy { it.occurency }.reversed().toList()
    }

    override fun getTheme() = R.style.AppBottomSheetDialogTheme
}