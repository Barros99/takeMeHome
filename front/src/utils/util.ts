import dotenv from "dotenv";

dotenv.config();

export const apiUrl =
  process.env.API_URL || "http://localhost:8080/api/animals";

export function convertDatetoLocaleDateString(date: string): string {
  const dateObj = new Date(date);
  const options: Intl.DateTimeFormatOptions = {
    year: "numeric",
    month: "long",
    day: "numeric",
  };

  dateObj.setDate(dateObj.getDate() + 1);

  return dateObj.toLocaleDateString("pt-BR", options);
}

export function convertDateToDdMmYyyy(date: string): string {
  const [year, month, day] = date.split("-");
  return `${day.padStart(2, "0")}/${month.padStart(2, "0")}/${year}`;
}

export function checkNumberAndAddPlural(number: number, word: string): string {
  return `${word}${number > 1 ? "s" : ""}`;
}
