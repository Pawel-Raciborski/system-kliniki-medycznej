export default function formatDate(birthDate: string) {
  return birthDate.split("-").reverse().join("-");
}
