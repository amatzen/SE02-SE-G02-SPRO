import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './partials/navbar/navbar.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import {MDBBootstrapModule} from "angular-bootstrap-md";
import { ProgramsComponent } from './pages/programs/programs.component';
import { CompaniesComponent } from './pages/companies/companies.component';
import { PeopleComponent } from './pages/people/people.component';
import { AdminComponent } from './pages/admin/admin.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ProgramsComponent,
    CompaniesComponent,
    PeopleComponent,
    AdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NoopAnimationsModule,
    MDBBootstrapModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
