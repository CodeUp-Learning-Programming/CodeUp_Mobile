package com.example.codeup.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codeup.R


@Composable
fun BotaoAzul(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    altura: Int = 56,
    largura: Int = 120,
    tamanhoFonte: Int = 16,
    loading: Boolean = false,

    ) {
    val interactionSource = remember { MutableInteractionSource() }

    val transition = updateTransition(
        targetState = loading,
        label = "master transition",
    )
    val horizontalContentPadding by transition.animateDp(
        transitionSpec = {
            spring(
                stiffness = SpringStiffness,
            )
        },
        targetValueByState = { toLoading -> if (toLoading) 12.dp else 24.dp },
        label = "button's content padding",
    )


    Box(
        modifier = modifier
            .size(width = largura.dp, height = altura.dp)
            .shadow(4.dp, RoundedCornerShape(1.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0, 225, 242),
                        Color(0, 132, 249)
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    onClick.invoke()
                }
            )
    ) {
        if(!loading){
            Text(
                text = text,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center),
                fontFamily = FontFamily(
                    Font(R.font.poppins_semi_bold, FontWeight.SemiBold)
                ),
                fontSize = tamanhoFonte.sp
            )
        }else{
            Box(contentAlignment = Alignment.Center) {
                LoadingContent(
                    loadingStateTransition = transition,
                )
                PrimaryContent(
                    loadingStateTransition = transition,
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun LoadingContent(
    loadingStateTransition: Transition<Boolean>,
) {
    loadingStateTransition.AnimatedVisibility(
        visible = { loading -> loading },
        enter = fadeIn(),
        exit = fadeOut(
            animationSpec = spring(
                stiffness = SpringStiffness,
                visibilityThreshold = 0.10f,
            ),
        ),
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(18.dp),
            color = LocalContentColor.current,
            strokeWidth = 1.5f.dp,
            strokeCap = StrokeCap.Round,
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun PrimaryContent(
    loadingStateTransition: Transition<Boolean>,
) {
    loadingStateTransition.AnimatedVisibility(
        visible = { loading -> !loading },
        enter = fadeIn() + expandHorizontally(
            animationSpec = spring(
                stiffness = SpringStiffness,
                dampingRatio = Spring.DampingRatioMediumBouncy,
                visibilityThreshold = IntSize.VisibilityThreshold,
            ),
            expandFrom = Alignment.CenterHorizontally,
        ),
        exit = fadeOut(
            animationSpec = spring(
                stiffness = SpringStiffness,
                visibilityThreshold = 0.10f,
            ),
        ) + shrinkHorizontally(
            animationSpec = spring(
                stiffness = SpringStiffness,
                // dampingRatio is not applicable here, size cannot become negative
                visibilityThreshold = IntSize.VisibilityThreshold,
            ),
            shrinkTowards = Alignment.CenterHorizontally,
        ),
    ) {
        Text(
            text = "Place the order",
            modifier = Modifier
                // so that bouncing button's width doesn't cut first and last letters
                .padding(horizontal = 4.dp),
        )
    }
}

// use same spring stiffness so that all animations finish at about the same time
private val SpringStiffness = Spring.StiffnessMediumLow
