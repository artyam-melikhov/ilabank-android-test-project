package com.d8corp.d8amelihovstest

import android.app.Dialog
import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomDialog(val items: List<ListItem>, val position: Int) : BottomSheetDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val occurencies = computeOccurencies(items)
        val view = ComposeView(requireContext()).apply { setContent {
            Column(modifier = Modifier.padding(all = 15.dp)) {
                DialogPosition(position)
                repeat(3) {
                    DialogOccurency(occurencies[it].char, occurencies[it].occurency)
                }
            }
        } }
        dialog.setContentView(view)

        view.setOnClickListener { dismiss() }

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