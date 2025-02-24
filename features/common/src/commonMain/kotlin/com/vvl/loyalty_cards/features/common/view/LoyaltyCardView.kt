package com.vvl.loyalty_cards.features.common.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.common.model.LoyaltyCardCodeType
import com.vvl.loyalty_cards.features.common.utils.toBarCodeType
import io.github.alexzhirkevich.qrose.oned.rememberBarcodePainter
import io.github.alexzhirkevich.qrose.options.QrBrush
import io.github.alexzhirkevich.qrose.options.QrColors
import io.github.alexzhirkevich.qrose.options.solid
import io.github.alexzhirkevich.qrose.rememberQrCodePainter

private const val CARD_ASPECT_RATIO = 85.6f / 53.98f

@Composable
fun LoyaltyCardView(
    modifier: Modifier,
    loyaltyCard: LoyaltyCard,
    onClick: ((LoyaltyCard) -> Unit)?,
) {
    var color by remember { mutableStateOf(Color.White) }
    Card(
        modifier = modifier.aspectRatio(CARD_ASPECT_RATIO),
        colors = CardDefaults.cardColors(containerColor = Color(loyaltyCard.cardColor)),
        onClick = {
            if (onClick != null) {
                onClick(loyaltyCard)
            } else {
                color = if (color == Color.Black) Color.White else Color.Black
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .alpha(if (loyaltyCard.name.isBlank()) 0f else 1f)
                    .padding(16.dp)
                    .background(
                        color = Color.White,
                        shape = CircleShape
                    )
                    .padding(8.dp),
                text = loyaltyCard.name,
                color = Color.Black,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
            val painter = if (loyaltyCard.codeType == LoyaltyCardCodeType.QR_CODE) {
                rememberQrCodePainter(
                    data = loyaltyCard.data,
                    colors = QrColors(dark = QrBrush.solid(color))
                )
            } else {
                rememberBarcodePainter(
                    data = loyaltyCard.data,
                    type = loyaltyCard.codeType.toBarCodeType(),
                    brush = SolidColor(color)
                )
            }

            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                painter = painter,
                contentDescription = "Localized description"
            )
        }
    }
}
