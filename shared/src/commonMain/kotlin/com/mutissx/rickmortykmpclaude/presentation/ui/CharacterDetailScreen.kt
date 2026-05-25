package com.mutissx.rickmortykmpclaude.presentation.ui

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mutissx.rickmortykmpclaude.ShowCharacterToastEffect
import com.mutissx.rickmortykmpclaude.domain.model.Character
import com.mutissx.rickmortykmpclaude.domain.model.Episode
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.Background
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.OnSurface
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.OnSurfaceVariant
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.OutlineVariant
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.PortalGreen
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.StatusAlive
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.StatusDead
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.StatusUnknown
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.SurfaceCard
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.SurfaceContainerLow
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.ToxicPurple
import com.mutissx.rickmortykmpclaude.presentation.viewmodel.CharacterDetailState
import com.mutissx.rickmortykmpclaude.presentation.viewmodel.CharacterDetailViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    characterId: Int,
    characterName: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: CharacterDetailViewModel = koinViewModel(
        key = characterId.toString(),
        parameters = { parametersOf(characterId) }
    )
    val state by viewModel.state.collectAsState()

    ShowCharacterToastEffect(characterName = characterName)

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = PortalGreen
                        )
                    }
                },
                title = {
                    Text(
                        text = characterName.uppercase(),
                        style = MaterialTheme.typography.headlineMedium,
                        color = PortalGreen
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Background.copy(alpha = 0.9f)
                )
            )
        },
        containerColor = Background
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val s = state) {
                is CharacterDetailState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = PortalGreen,
                        strokeWidth = 2.dp
                    )
                }

                is CharacterDetailState.Error -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "SIGNAL LOST",
                            style = MaterialTheme.typography.headlineMedium,
                            color = PortalGreen
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            s.message,
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { viewModel.loadCharacter() },
                            shape = ClipNotchShape,
                            colors = ButtonDefaults.buttonColors(containerColor = PortalGreen)
                        ) {
                            Text("RETRY SCAN", color = Color(0xFF203600))
                        }
                    }
                }

                is CharacterDetailState.Success -> {
                    DetailContent(character = s.character, episodes = s.episodes)
                }
            }
        }
    }
}

@Composable
private fun DetailContent(character: Character, episodes: List<Episode>) {
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Hero: circular image with portal glow + status badge below
        Box(contentAlignment = Alignment.BottomCenter) {
            Box(
                modifier = Modifier
                    .size(240.dp)
                    .background(
                        Brush.radialGradient(
                            listOf(PortalGreen.copy(alpha = 0.25f), Color.Transparent)
                        ),
                        CircleShape
                    )
                    .padding(12.dp)
            ) {
                AsyncImage(
                    model = character.imageUrl,
                    contentDescription = character.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .border(2.dp, PortalGreen, CircleShape)
                )
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 0.dp)
                    .background(SurfaceCard, RoundedCornerShape(100.dp))
                    .border(1.dp, PortalGreen.copy(alpha = 0.5f), RoundedCornerShape(100.dp))
                    .padding(horizontal = 14.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(statusColor.copy(alpha = pulse))
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = character.status.uppercase(),
                    style = MaterialTheme.typography.labelLarge,
                    color = OnSurface
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // DIMENSIONAL IDENTITY card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(ClipCornerShape)
                .background(SurfaceCard)
                .padding(24.dp)
        ) {
            // Globe watermark
            Text(
                text = "⊕",
                style = MaterialTheme.typography.displayMedium,
                color = OnSurface.copy(alpha = 0.07f),
                modifier = Modifier.align(Alignment.TopEnd)
            )

            Column {
                // Header
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text("◈", style = MaterialTheme.typography.labelLarge, color = ToxicPurple)
                    Text(
                        "DIMENSIONAL IDENTITY",
                        style = MaterialTheme.typography.labelLarge,
                        color = ToxicPurple
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Species + Gender 2-column
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "SPECIES",
                            style = MaterialTheme.typography.labelSmall,
                            color = OnSurfaceVariant.copy(alpha = 0.6f)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            character.species,
                            style = MaterialTheme.typography.bodyLarge,
                            color = OnSurface
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "GENDER",
                            style = MaterialTheme.typography.labelSmall,
                            color = OnSurfaceVariant.copy(alpha = 0.6f)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            character.gender,
                            style = MaterialTheme.typography.bodyLarge,
                            color = OnSurface
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                Divider()
                Spacer(modifier = Modifier.height(16.dp))

                // Origin planet
                Text(
                    "ORIGIN PLANET",
                    style = MaterialTheme.typography.labelSmall,
                    color = OnSurfaceVariant.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        character.originName,
                        style = MaterialTheme.typography.bodyLarge,
                        color = PortalGreen
                    )
                    Text("🚀", style = MaterialTheme.typography.bodyLarge)
                }

                Spacer(modifier = Modifier.height(16.dp))
                Divider()
                Spacer(modifier = Modifier.height(16.dp))

                // Last known location
                Text(
                    "LAST KNOWN LOCATION",
                    style = MaterialTheme.typography.labelSmall,
                    color = OnSurfaceVariant.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        character.locationName,
                        style = MaterialTheme.typography.bodyLarge,
                        color = OnSurface
                    )
                    Text("📍", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }

        if (episodes.isNotEmpty()) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "RECENT ACTIVITY",
                style = MaterialTheme.typography.headlineMedium,
                color = OnSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                episodes.forEach { episode ->
                    EpisodeRow(episode = episode)
                }
            }

        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun Divider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(OutlineVariant.copy(alpha = 0.2f))
    )
}

@Composable
private fun EpisodeRow(episode: Episode) {
    val borderColor = PortalGreen
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceContainerLow)
            .drawBehind {
                drawRect(
                    color = borderColor,
                    topLeft = Offset.Zero,
                    size = Size(4.dp.toPx(), size.height)
                )
            }
            .padding(start = 20.dp, top = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = episode.episode,
                style = MaterialTheme.typography.labelLarge,
                color = ToxicPurple
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = episode.name,
                style = MaterialTheme.typography.bodyMedium,
                color = OnSurface
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "TIMESTAMP",
                style = MaterialTheme.typography.labelSmall,
                color = OnSurfaceVariant.copy(alpha = 0.4f)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = episode.airDate.uppercase(),
                style = MaterialTheme.typography.bodySmall,
                color = OnSurface.copy(alpha = 0.6f)
            )
        }
    }
}
