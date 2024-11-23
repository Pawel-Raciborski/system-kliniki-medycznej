export interface SearchMedicineData {
  medicinalProductName: string;
  advancedSearchOptions?: {
    atcCode: string;
    gtinNumber: string;
    commonName: string;
  }
}
