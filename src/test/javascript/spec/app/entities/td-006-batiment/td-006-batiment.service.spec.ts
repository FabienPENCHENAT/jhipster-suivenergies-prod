import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TD006BatimentService } from 'app/entities/td-006-batiment/td-006-batiment.service';
import { ITD006Batiment, TD006Batiment } from 'app/shared/model/td-006-batiment.model';

describe('Service Tests', () => {
  describe('TD006Batiment Service', () => {
    let injector: TestBed;
    let service: TD006BatimentService;
    let httpMock: HttpTestingController;
    let elemDefault: ITD006Batiment;
    let expectedResult: ITD006Batiment | ITD006Batiment[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TD006BatimentService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TD006Batiment(0, 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TD006Batiment', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TD006Batiment()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TD006Batiment', () => {
        const returnedFromService = Object.assign(
          {
            besoinChauffage: 1,
            deperditionEnveloppe: 1,
            deperditionRenouvellementAir: 1,
            altitude: 1,
            nombreNiveau: 1,
            hspMoyenne: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TD006Batiment', () => {
        const returnedFromService = Object.assign(
          {
            besoinChauffage: 1,
            deperditionEnveloppe: 1,
            deperditionRenouvellementAir: 1,
            altitude: 1,
            nombreNiveau: 1,
            hspMoyenne: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TD006Batiment', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
