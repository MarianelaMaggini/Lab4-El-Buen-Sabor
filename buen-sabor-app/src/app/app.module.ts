import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';

// Peticiones HTTP
import { HttpClientModule } from '@angular/common/http';

// Formularios
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// Rutas
import { AppRoutingModule } from './app-routing.module';

// Header
import { HeaderComponent } from './components/header/header.component';

// Footer
import { FooterComponent } from './components/footer/footer.component';

// Home
import { HomeComponent } from './components/home/home.component';

// Carrito
import { CartComponent } from './components/cart/cart.component';

// Detalles del Carrito
import { DetallePedidoComponent } from './components/detalle-pedido/detalle-pedido.component';

// Articulos
import { ArticulosComponent } from './components/articulos/articulos.component';

// Item Articulo
import { ItemArticuloComponent } from './components/item-articulo/item-articulo.component';

// Login y Registro de usuario
import { LoginComponent } from './components/auth/login/login.component';
import { RegistroComponent } from './components/auth/registro/registro.component';

// Imports externos
// Alertas
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

// Servicios
// Servicio de Articulo
import { ArticuloService } from './services/articulo.service';

// Servicio de Mercado Pago
import { MercadoPagoService } from './services/mercado-pago.service';


// Interceptor
import { interceptorProvider } from './interceptors/prod-interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    CartComponent,
    DetallePedidoComponent,
    ArticulosComponent,
    ItemArticuloComponent,
    LoginComponent,
    RegistroComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot(), // ToastrModule added
  ],
  providers: [ArticuloService, MercadoPagoService,interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
