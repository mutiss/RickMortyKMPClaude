package com.mutissx.rickmortykmpclaude.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.Background
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.OnSurface
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.OnSurfaceVariant
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.PortalGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeDialog(onContinue: () -> Unit) {
    BasicAlertDialog(onDismissRequest = {}) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Background, RoundedCornerShape(16.dp))
                .border(1.dp, PortalGreen, RoundedCornerShape(16.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Welcome to Rick & Morty Project",
                color = PortalGreen,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Explore characters across infinite dimensions.",
                color = OnSurfaceVariant,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(24.dp))
            TextButton(
                onClick = onContinue,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PortalGreen, RoundedCornerShape(8.dp)),
            ) {
                Text(
                    text = "Continue",
                    color = OnSurface,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}
