import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProgramsComponent} from "./pages/programs/programs.component";
import {CompaniesComponent} from "./pages/companies/companies.component";
import {PeopleComponent} from "./pages/people/people.component";
import {AdminComponent} from "./pages/admin/admin.component";

const routes: Routes = [
  {path: "", redirectTo: "/programs", pathMatch: "full"},
  {path: "programs", component: ProgramsComponent },
  {path: "companies", component: CompaniesComponent },
  {path: "people", component: PeopleComponent },
  {path: "admin", component: AdminComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
