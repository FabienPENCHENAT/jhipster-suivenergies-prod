import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TD017ConsommationNeufService } from 'app/entities/td-017-consommation-neuf/td-017-consommation-neuf.service';
import { ITD017ConsommationNeuf, TD017ConsommationNeuf } from 'app/shared/model/td-017-consommation-neuf.model';

describe('Service Tests', () => {
  describe('TD017ConsommationNeuf Service', () => {
    let injector: TestBed;
    let service: TD017ConsommationNeufService;
    let httpMock: HttpTestingController;
    let elemDefault: ITD017ConsommationNeuf;
    let expectedResult: ITD017ConsommationNeuf | ITD017ConsommationNeuf[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TD017ConsommationNeufService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TD017ConsommationNeuf(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TD017ConsommationNeuf', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TD017ConsommationNeuf()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TD017ConsommationNeuf', () => {
        const returnedFromService = Object.assign(
          {
            tr004TypeEnergie: 'BBBBBB',
            tr006TypeUsage: 'BBBBBB',
            tv044ConversionKwhEnergiesRelevees: 'BBBBBB',
            tv045ConversionKwhCo2: 'BBBBBB',
            tv046EvaluationContenuCo2Reseaux: 'BBBBBB',
            consommationEnergieFinale: 1,
            consommationEnergiePrimaire: 1,
            fraisAnnuelsEnergie: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TD017ConsommationNeuf', () => {
        const returnedFromService = Object.assign(
          {
            tr004TypeEnergie: 'BBBBBB',
            tr006TypeUsage: 'BBBBBB',
            tv044ConversionKwhEnergiesRelevees: 'BBBBBB',
            tv045ConversionKwhCo2: 'BBBBBB',
            tv046EvaluationContenuCo2Reseaux: 'BBBBBB',
            consommationEnergieFinale: 1,
            consommationEnergiePrimaire: 1,
            fraisAnnuelsEnergie: 1,
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

      it('should delete a TD017ConsommationNeuf', () => {
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
