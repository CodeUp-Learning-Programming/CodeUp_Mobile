package com.example.codeup.ui.composables.componentes

import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.extensions.formatToSinglePrecision
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine

@Composable
fun LineChartScreen(pointsData: List<Point>) {
    val steps = pointsData.size


    val meses = listOf(
        "JAN", "FEV", "MAR", "ABR",
        "MAI", "JUN", "JUL", "AGO",
        "SET", "OUT", "NOV", "DEZ"
    )
    // Mapeia os valores de x para os rótulos dos meses
    val monthLabels = pointsData.map { point ->
        val monthIndex = point.x.toInt() - 1 // x deve estar entre 1 e 12
        if (monthIndex in 0..11) meses[monthIndex] else ""
    }


    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .backgroundColor(Color.Transparent)
        .steps(pointsData.size - 1)
        .labelData { i -> monthLabels.getOrElse(i) { "" } }
        .labelAndAxisLinePadding(20.dp)
        .axisLineColor(Color.White)
        .axisLabelColor(Color.White)
        .backgroundColor(Color.Black)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .axisStepSize(100.dp)
        .axisLineColor(Color.White)
        .axisLabelColor(Color.White)
        .backgroundColor(Color.Black)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            // Adiciona yMin para obter os valores negativos do eixo à escala
            val yMin = pointsData.minOf { it.y }
            val yMax = pointsData.maxOf { it.y }
            val yScale = (yMax - yMin) / steps
            ((i * yScale) + yMin).formatToSinglePrecision()
        }.build()

    val lineChartData = LineChartData(
        paddingRight = 0.dp,
        paddingTop = 50.dp,
        bottomPadding = 5.dp,
        isZoomAllowed = true,
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    lineStyle = LineStyle(
                        color = Color(0, 154, 242),
                        lineType = LineType.SmoothCurve(isDotted = false)
                    ),
                    intersectionPoint = IntersectionPoint(
                        color = Color(0, 154, 242)
                    ),
                    selectionHighlightPoint = SelectionHighlightPoint(
                        color = Color(0, 154, 242)
                    ),
                    shadowUnderLine = ShadowUnderLine(
                        alpha = 0.5f,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0, 154, 242),
                                Color.Transparent
                            )
                        )
                    ),
                    selectionHighlightPopUp = SelectionHighlightPopUp(
                        popUpLabel = { _, y ->
                            String.format("%.0f", y)
                        },
                        backgroundColor = Color.Black,
                        labelColor = Color.White,
                        labelAlignment = Paint.Align.CENTER,
                        labelSize = 14.sp

                    )
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(Color.Transparent),
        backgroundColor = Color.Black
    )

    LineChart(
        modifier = Modifier
            .background(Color.Black, shape = RoundedCornerShape(8.dp)),
        lineChartData = lineChartData
    )


}
