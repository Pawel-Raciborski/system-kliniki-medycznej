export default function formatDate(birthDate: string) {
  console.log(birthDate);
  let s = birthDate.split("-").reverse().join(".");
  console.log(s);
  return s;
}
