import {BaseModel} from '../base.model';
import {AuthorModel} from './author.model';

export class BookModel extends BaseModel {
  name: string;
  description: string;
  dateOfBirth: string;
  imageUrl: string;
  publicationYear: any;
  author: AuthorModel;
  edition: any;
  category: any;
}
