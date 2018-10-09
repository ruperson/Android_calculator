package ru.ifmo.ctddev.vanyan.myawesomecalculator

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.udojava.evalex.Expression
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClicked(v: View) {
        currentExpression.append((v as Button ).text.toString())
        currentResult.text = ""
        //evaluate(v)
    }

    fun clear(v : View) {
        if (v.id == R.id.clear) {
            if (currentExpression.text.lastIndex != -1) {
                val res = currentExpression.text.toString().subSequence(0, currentExpression.text.lastIndex)
                currentExpression.text = res
            }
        } else {
            currentExpression.text  = ""
        }
        currentResult.text = ""
    }
    fun evaluate(v : View) {
        try {
            val result = Expression(currentExpression.text.toString()).eval()
            currentResult.text = result.toString()
        } catch (error: Exception) {
            currentResult.text = "ERROR"
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("0", currentExpression.text.toString())
        outState.putString("1", currentResult.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentExpression.text = savedInstanceState.getString("0")
        currentResult.text = savedInstanceState.getString("1")
    }
}
