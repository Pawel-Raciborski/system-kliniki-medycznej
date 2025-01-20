export interface DoctorSearchOptions {
  doctorFullName: string;
  advancedSearch: {
    pwzNumber: string,
    selectedSpecializations: string[]
  } | null;
}
