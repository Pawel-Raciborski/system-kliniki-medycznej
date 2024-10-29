export interface DoctorSearchOptions {
  searchValue: string;
  advancedSearch: {
    pwzNumber: string,
    selectedSpecializations: string[]
  } | null;
}
