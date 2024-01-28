package com.psychojean.core.impl.presentation.ui.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PairText(
    modifier: Modifier = Modifier,
    firstTextModifier: Modifier = Modifier,
    secondTextModifier: Modifier = Modifier,
    firstTextStyle: TextStyle = MaterialTheme.typography.titleMedium,
    secondTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    firstText: String,
    secondText: String,
) {
    Row(
        modifier = modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = firstTextModifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            text = firstText,
            style = firstTextStyle,
            textAlign = TextAlign.Start
        )
        Text(
            modifier = secondTextModifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            text = secondText,
            style = secondTextStyle,
            textAlign = TextAlign.End
        )
    }
}

@Composable
@Preview
private fun PairTextPreview() {
    PairText(firstText = "Key", secondText = "Value")
}