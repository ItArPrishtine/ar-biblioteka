import {BookCategory} from '../book/category.model';

export class BookCategoryEnum {
  static readonly HISTORI = new BookCategory('Histori', 'H');
  static readonly ANGLEZE = new BookCategory('Angleze', 'A');
  static readonly FILOZOFIK = new BookCategory('Filozofik', 'F');
  static readonly PSIKOLOGJIK = new BookCategory('Psikologjik', 'P');
  static readonly BIOGRAFI = new BookCategory('Biografi', 'B');
  static readonly AKROPOLI = new BookCategory('Akropoli i Ri', 'A.R');
  static readonly L_HUAJ = new BookCategory('Letersi e Huaj', 'L.H');
  static readonly L_SHQIPE = new BookCategory('Letersi Shqipe', 'L.SH');
  static readonly MITOLOGJI = new BookCategory('Mitologji', 'M');
  static readonly EF = new BookCategory('Enciklopedi dhe Fjalor', 'E.F');
  static readonly K = new BookCategory('Konspiracion', 'K');
}
