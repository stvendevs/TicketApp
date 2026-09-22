package com.example.ticketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width 
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TicketApp()
        }
    }
}

@Composable
fun TicketApp() {

    val hargaTiket = 25000
    var jumlahTiket by remember {
        mutableStateOf(1)
    }

    val totalBayar = hargaTiket * jumlahTiket

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Judul
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "🎟",
            fontSize = 50.sp
        )

        Text(
            text = "Pemesanan Tiket",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1976D2)
        )

        Text(
            text = "Pesan tiket dengan mudah!",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Harga Tiket
        TicketCard(
            title = "Harga Tiket"
        ) {
            Text(
                text = formatRupiah(hargaTiket),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2)
            )

            Text(
                text = "per tiket",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Jumlah Tiket
        TicketCard(
            title = "Jumlah Tiket"
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Button(
                    onClick = {
                        if (jumlahTiket > 1) {
                            jumlahTiket--
                        }
                    },
                    shape = RoundedCornerShape(50)
                ) {
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = "Kurangi tiket"
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(65.dp)
                        .background(
                            Color(0xFFF0F2F5),
                            RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = jumlahTiket.toString(),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                Button(
                    onClick = {
                        jumlahTiket++
                    },
                    shape = RoundedCornerShape(50)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Tambah tiket"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Total Bayar
        TicketCard(
            title = "Total"
        ) {
            Text(
                text = formatRupiah(totalBayar),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF168A4A)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Reset
        Button(
            onClick = {
                jumlahTiket = 1
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Reset"
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "RESET",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TicketCard(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White,
                RoundedCornerShape(16.dp)
            )
            .padding(20.dp)
    ) {

        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF172B4D)
        )

        Spacer(modifier = Modifier.height(10.dp))

        content()
    }
}

fun formatRupiah(nominal: Int): String {
    return "Rp" + String.format("%,d", nominal)
        .replace(",", ".")
}