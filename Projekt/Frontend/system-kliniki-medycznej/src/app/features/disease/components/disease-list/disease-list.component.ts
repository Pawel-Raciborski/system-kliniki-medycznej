import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {
  SearchMedicineBarComponent
} from '../../../prescriptions/components/search-medicine-bar/search-medicine-bar.component';
import {SearchDiseaseComponent} from '../search-disease/search-disease.component';
import {DiseaseDto} from '../../model/disease-dto';
import {DiseaseService} from '../../disease.service';
import {Pagination} from '../../../pagination/model/pagination';
import {DoctorSearchOptions} from '../../../doctor/components/search-bar/model/doctor-search-options';
import {SearchDisease} from '../../model/search-disease';
import {DiseaseTableComponent} from '../disease-table/disease-table.component';
import {MatDialog, MatDialogContent} from '@angular/material/dialog';
import {
  CreatePatientDiseaseDescriptionDialogComponent
} from '../../../patient-disease/dialogs/create-patient-disease-description/create-patient-disease-description-dialog.component';
import {MedicineDto} from '../../../medicine/model/medicine-dto';
import {CreatePatientDiseasePart} from '../../model/create-patient-disease-part';
import {PaginationBarComponent} from '../../../pagination/components/pagination-bar/pagination-bar.component';

@Component({
  selector: 'app-disease-list',
  standalone: true,
  imports: [
    SearchMedicineBarComponent,
    SearchDiseaseComponent,
    DiseaseTableComponent,
    MatDialogContent,
    PaginationBarComponent
  ],
  templateUrl: './disease-list.component.html',
  styleUrl: './disease-list.component.css'
})
export class DiseaseListComponent implements OnInit {
  diseases: DiseaseDto[] = [];
  pagination: Pagination = {
    page: 0,
    pageSize: 20
  };
  lastLoadedPageSize = 20;
  diseaseSearch: SearchDisease = {
    name: '',
    code: ''
  };

  @Output() selectedDiseaseWithDescriptionEmitter = new EventEmitter<CreatePatientDiseasePart>();

  constructor(
    private diseaseService: DiseaseService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.loadData(this.diseaseSearch);
  }

  public loadData(diseaseOptions: SearchDisease) {
    this.diseaseSearch = {...diseaseOptions};
    this.diseaseService.findPagedDiseases(diseaseOptions, this.pagination).subscribe(data => {
      this.lastLoadedPageSize = data.length;
      this.diseases = data;
    });
  }

  onDiseaseSearch(searchDisease: SearchDisease) {
    this.clearPagination();
    this.loadData(searchDisease);
  }

  private clearPagination() {
    this.pagination.page = 0;
    this.pagination.pageSize = 20;
  }

  onDiseaseAdd(diseaseDto: DiseaseDto) {
    this.removeDiseaseFromArray(diseaseDto);
    this.dialog.open(CreatePatientDiseaseDescriptionDialogComponent, {
      data: diseaseDto
    }).afterClosed().subscribe((data: CreatePatientDiseasePart) => {
      if (data) {
        this.selectedDiseaseWithDescriptionEmitter.emit(data);
      }
    })
  }

  private removeDiseaseFromArray(diseaseDto: DiseaseDto) {
    this.diseases = this.diseases.filter(d => d !== diseaseDto);
  }

  loadPage(page: number) {
    console.log(page, this.pagination.page)
    if(page < this.pagination.page ){
      this.pagination.page = page;
      this.loadData(this.diseaseSearch);
    }else if(page > this.pagination.page){
      this.pagination.page = page;
      this.loadMore(this.diseaseSearch);
    }
  }

  private loadMore(diseaseSearch: SearchDisease) {
    this.diseaseSearch = {...diseaseSearch};
    this.diseaseService.findPagedDiseases(diseaseSearch, this.pagination).subscribe(data => {
      this.lastLoadedPageSize = data.length;
      this.diseases = [...this.diseases,...data];
    });
  }
}
