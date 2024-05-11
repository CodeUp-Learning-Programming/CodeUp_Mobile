package com.example.codeup.ui.composables.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarStyle

@Composable
fun BarChartScreen(

) {
    val stepSize = 5
    val barsData = listOf(
        BarData(Point(0.toFloat(), 2f), label = "Jan", color = Color(0, 154, 242)),
        BarData(Point(1.toFloat(), 5f), label = "Fev" , color = Color(0, 154, 242)),
        BarData(Point(2.toFloat(), 1f), label = "Mar" , color = Color(0, 154, 242)),
        BarData(Point(3.toFloat(), 3f), label = "Abr" , color = Color(0, 154, 242)),
        BarData(Point(4.toFloat(), 6f), label = "Mai" , color = Color(0, 154, 242)),
        BarData(Point(5.toFloat(), 7f), label = "Mar" , color = Color(0, 154, 242)),
        BarData(Point(6.toFloat(), 4f), label = "Abr" , color = Color(0, 154, 242)),
        BarData(Point(7.toFloat(), 7f), label = "Mai" , color = Color(0, 154, 242)),
        BarData(Point(8.toFloat(), 9f), label = "Mar" , color = Color(0, 154, 242)),
        BarData(Point(9.toFloat(), 10f), label = "Abr" , color = Color(0, 154, 242)),
        BarData(Point(10.toFloat(), 14f), label = "Mai" , color = Color(0, 154, 242))
    )

        val xAxisData = AxisData.Builder ()
        .axisStepSize(100.dp)
        .steps(barsData.size - 1)
        .bottomPadding(40.dp)
        .axisLabelAngle(20f)
        .labelData { index -> barsData[index].label }
        .axisLineColor(Color.White)
        .axisLabelColor(Color.White)
        .backgroundColor(Color.Black)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(stepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * 20).toFloat().toString() }
        .axisLineColor(Color.White)
        .axisLabelColor(Color.White)
        .backgroundColor(Color.Black)

        .build()


    val barChartData = BarChartData(
        chartData = barsData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        barStyle = BarStyle(

        ),
        backgroundColor = Color.Black,

    )

    BarChart(
        modifier = Modifier
            .height(350.dp)
            .background(Color.Black),
        barChartData = barChartData
    )
}