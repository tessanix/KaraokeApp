package com.mizikarocco.karaokeapp.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomToast(message: String, imageVector: ImageVector ,animatableAlpha: Animatable<Float, AnimationVector1D>?, resetToastDisplay : () -> Unit) {

    if(animatableAlpha!=null) {

        LaunchedEffect(key1 = animatableAlpha) {
            animatableAlpha.animateTo(
                targetValue = 0F,
                animationSpec = tween(4000)
            )
            resetToastDisplay()
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp),
        contentAlignment = Alignment.BottomCenter

    ) {
        ToastContent(message, imageVector, alpha = animatableAlpha?.value)
    }
}


@Composable
fun ToastContent(message: String, imageVector: ImageVector, alpha:Float?=null){

    Row(
        Modifier
            .background(color= Color.White)
            .alpha(alpha ?: 1F)
            .padding(16.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            message,
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}