package com.mutissx.rickmortykmpclaude.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.collectAsLazyPagingItems
import app.cash.paging.compose.itemKey
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.Background
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.OnSurface
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.OnSurfaceVariant
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.PortalGreen
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.SurfaceCard
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.SurfaceContainerLow
import com.mutissx.rickmortykmpclaude.presentation.viewmodel.CharacterViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterViewModel = koinViewModel(),
    onCharacterClick: (Int, String) -> Unit = { _, _ -> }
) {
    val characters = viewModel.characters.collectAsLazyPagingItems()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "⬡",
                            style = MaterialTheme.typography.headlineMedium,
                            color = PortalGreen,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = "CHARACTER DIRECTORY",
                            style = MaterialTheme.typography.headlineMedium,
                            color = PortalGreen
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Background.copy(alpha = 0.9f)
                )
            )
        },
        containerColor = Background
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchTerminal(
                query = searchQuery,
                onQueryChange = viewModel::onSearchQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )

            Box(modifier = Modifier.fillMaxSize()) {
                val refreshError = characters.loadState.refresh as? LoadState.Error

                when {
                    characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = PortalGreen,
                            strokeWidth = 2.dp
                        )
                    }

                    refreshError != null && characters.itemCount == 0 -> {
                        Column(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "SIGNAL LOST",
                                style = MaterialTheme.typography.headlineMedium,
                                color = PortalGreen
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = refreshError.error.message ?: "Unable to reach dimension C-137",
                                style = MaterialTheme.typography.bodySmall,
                                color = OnSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = { characters.retry() },
                                shape = ClipNotchShape,
                                colors = ButtonDefaults.buttonColors(containerColor = PortalGreen)
                            ) {
                                Text(
                                    "RETRY TRANSMISSION",
                                    color = Color(0xFF203600),
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }
                        }
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(vertical = 4.dp)
                        ) {
                            if (refreshError != null) {
                                item {
                                    OfflineBanner(onRetry = { characters.retry() })
                                }
                            }

                            items(
                                count = characters.itemCount,
                                key = characters.itemKey { it.id }
                            ) { index ->
                                characters[index]?.let { character ->
                                    CharacterCard(
                                        character = character,
                                        onClick = { onCharacterClick(character.id, character.name) }
                                    )
                                }
                            }

                            if (characters.loadState.append is LoadState.Loading) {
                                item {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(24.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(12.dp)
                                    ) {
                                        CircularProgressIndicator(
                                            color = PortalGreen,
                                            strokeWidth = 2.dp,
                                            modifier = Modifier.size(40.dp)
                                        )
                                        Text(
                                            text = "TRANSMITTING MORE DATA...",
                                            style = MaterialTheme.typography.labelMedium,
                                            color = OnSurfaceVariant
                                        )
                                    }
                                }
                            }

                            if (characters.loadState.append is LoadState.Error) {
                                val e = (characters.loadState.append as LoadState.Error).error
                                item {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = e.message ?: "Error loading more",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = OnSurfaceVariant
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Button(
                                            onClick = { characters.retry() },
                                            shape = ClipNotchShape,
                                            colors = ButtonDefaults.buttonColors(containerColor = PortalGreen)
                                        ) {
                                            Text("RETRY", color = Color(0xFF203600))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun OfflineBanner(onRetry: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceCard)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "OFFLINE — SHOWING CACHED DATA",
            style = MaterialTheme.typography.labelSmall,
            color = OnSurfaceVariant
        )
        TextButton(onClick = onRetry) {
            Text("RETRY", color = PortalGreen, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
private fun SearchTerminal(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = PortalGreen
    Row(
        modifier = modifier
            .background(SurfaceContainerLow, RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
            .drawBehind {
                drawLine(
                    color = borderColor,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 2.dp.toPx()
                )
            }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search",
            tint = PortalGreen,
            modifier = Modifier.size(20.dp)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            if (query.isEmpty()) {
                Text(
                    text = "Search dimension...",
                    style = MaterialTheme.typography.bodyLarge,
                    color = OnSurfaceVariant.copy(alpha = 0.4f)
                )
            }
            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = OnSurface),
                cursorBrush = SolidColor(PortalGreen),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }
    }
}
