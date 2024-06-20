package com.example.contactbook.ui_layer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactbook.R
import com.example.contactbook.ui.theme.GrayColor
import com.example.contactbook.ui.theme.PrimaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    state: ContactState,
    viewModel: ContactViewModel,
    onEvent: () -> Unit
) {

    var dialogShow by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.White,

        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.sort_icon),
                            contentDescription = " Sort Icon",
                            modifier = Modifier.clickable {
                                viewModel.changeSorting()
                            }
                        )
                        Text(
                            text = "All Contacts",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = PrimaryColor,
                onClick = {
                    dialogShow = true
                }) {
                Image(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add New Contact Icon",
                    modifier = Modifier.size(32.dp),
                    colorFilter = ColorFilter.tint(color = Color.White)
                )
            }
        },

        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)

    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(innerPadding)
        ) {
            items(state.contact) {
                ShowContactCard(name = it.name, number = it.number, gmail = it.gmail)
            }

        }
    }

    if (dialogShow) {

        AlertDialog(
            containerColor = Color.White,
            onDismissRequest = { dialogShow = false },
            confirmButton = { },

            title = {
                Text(
                    text = "Add New Contact",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },

            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.add_image_icon),
                        contentDescription = " Add Image Icon",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(
                                shape = CircleShape
                            )

                    )

                    TextField(
                        value = state.name.value,
                        onValueChange = {
                            state.name.value = it
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.person_icon),
                                contentDescription = " Persson Icon"
                            )
                        },
                        placeholder = {
                            Text(text = "Enter name")
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = PrimaryColor,
                            unfocusedTextColor = Color.Black,
                            focusedLeadingIconColor = PrimaryColor,
                            unfocusedLeadingIconColor = Color.Black,
                            unfocusedIndicatorColor = PrimaryColor,
                            focusedIndicatorColor = PrimaryColor
                        )
                    )


                    TextField(
                        value = state.number.value,
                        onValueChange = {
                            state.number.value = it
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.phone_icon),
                                contentDescription = " Phone Icon"
                            )
                        },
                        placeholder = {
                            Text(text = "Enter number")
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = PrimaryColor,
                            unfocusedTextColor = Color.Black,
                            focusedLeadingIconColor = PrimaryColor,
                            unfocusedLeadingIconColor = Color.Black,
                            unfocusedIndicatorColor = PrimaryColor,
                            focusedIndicatorColor = PrimaryColor
                        )
                    )


                    TextField(
                        value = state.gmail.value,
                        onValueChange = {
                            state.gmail.value = it
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.gmail_icon),
                                contentDescription = " Gmail Icon"
                            )
                        },
                        placeholder = {
                            Text(text = "Enter gmail")
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = PrimaryColor,
                            unfocusedTextColor = Color.Black,
                            focusedLeadingIconColor = PrimaryColor,
                            unfocusedLeadingIconColor = Color.Black,
                            unfocusedIndicatorColor = PrimaryColor,
                            focusedIndicatorColor = PrimaryColor
                        )
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CustomButton(btnTex = "Cancel", color = GrayColor) {
                            dialogShow = false
                        }
                        CustomButton(btnTex = "Save", color = PrimaryColor) {
                            onEvent.invoke()
                            dialogShow = false
                        }
                    }
                }
            }
        )
    }

}

@Composable
fun ShowContactCard(

    name: String,
    number: String,
    gmail: String
) {

    var dropDownShow by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(
                width = 2.dp,
                color = GrayColor,
                shape = RoundedCornerShape(10.dp)
            )
            .shadow(
                elevation = 5.dp,
                spotColor = Color.Black,
                shape = RoundedCornerShape(10.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.my_image),
                contentDescription = "Contact Image",
                modifier = Modifier
                    .size(50.dp)
                    .border(
                        width = 2.dp,
                        color = PrimaryColor,
                        shape = CircleShape
                    )
                    .clip(
                        shape = CircleShape
                    ),
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = name,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = number,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = gmail,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    color = Color.Gray
                )
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                Image(
                    painter = painterResource(id = R.drawable.more_icon),
                    contentDescription = " More Icon ",
                    modifier = Modifier.clickable {
                        dropDownShow = true
                    }
                )

                DropdownMenu(expanded = dropDownShow, onDismissRequest = {
                    dropDownShow = false
                }) {

                }

                Image(
                    painter = painterResource(id = R.drawable.call_icon),
                    contentDescription = " Call Icon "
                )
            }
        }
    }

}

@Composable
fun CustomButton(

    btnTex: String,
    color: Color,
    onClick: () -> Unit
) {

    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .height(42.dp)
            .width(120.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        )
    )
    {
        Text(text = btnTex)
    }

}