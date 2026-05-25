package com.mutissx.rickmortykmpclaude.presentation.ui

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mutissx.rickmortykmpclaude.domain.model.Character
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.OnPrimary
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.OnSurface
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.OnSurfaceVariant
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.OutlineVariant
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.PortalGreen
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.StatusAlive
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.StatusDead
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.StatusUnknown
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.SurfaceCard
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.SurfaceContainerHigh

@Composable
fun CharacterCard(
    character: Character,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val statusColor = when (character.status.lowercase()) {
        "alive" -> StatusAlive
        "dead" -> StatusDead
        else -> StatusUnknown
    }
    val pulse by rememberInfiniteTransition(label = "pulse").animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "pulse_alpha"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.3f)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            AsyncImage(
                model = character.imageUrl,
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(150.dp)
                    .fillMaxHeight()
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = PortalGreen,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    StatusPill(status = character.status, color = statusColor, pulseAlpha = pulse)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "SPECIES",
                    style = MaterialTheme.typography.labelSmall,
                    color = OnSurfaceVariant.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                SpeciesChip(species = character.species)

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "LAST KNOWN LOCATION",
                    style = MaterialTheme.typography.labelSmall,
                    color = OnSurfaceVariant.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = character.locationName,
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurface
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onClick,
                    modifier = Modifier.fillMaxWidth(),
                    shape = ClipNotchShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PortalGreen,
                        contentColor = OnPrimary
                    )
                ) {
                    Text(
                        text = "VIEW DOSSIER",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Composable
private fun StatusPill(status: String, color: Color, pulseAlpha: Float) {
    Row(
        modifier = Modifier
            .background(color.copy(alpha = 0.1f), RoundedCornerShape(100.dp))
            .padding(horizontal = 8.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = pulseAlpha))
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = status.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = color
        )
    }
}

@Composable
private fun SpeciesChip(species: String) {
    val borderColor = PortalGreen
    Box(
        modifier = Modifier
            .clip(ClipNotchShape)
            .background(SurfaceContainerHigh)
            .drawBehind {
                drawLine(
                    color = borderColor,
                    start = Offset(size.width - 1.dp.toPx(), 0f),
                    end = Offset(size.width - 1.dp.toPx(), size.height),
                    strokeWidth = 2.dp.toPx()
                )
            }
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = species,
            style = MaterialTheme.typography.bodySmall,
            color = OnSurface
        )
    }
}
