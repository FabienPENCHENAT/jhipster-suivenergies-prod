import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TD014GenerateurECSService } from 'app/entities/td-014-generateur-ecs/td-014-generateur-ecs.service';
import { ITD014GenerateurECS, TD014GenerateurECS } from 'app/shared/model/td-014-generateur-ecs.model';

describe('Service Tests', () => {
  describe('TD014GenerateurECS Service', () => {
    let injector: TestBed;
    let service: TD014GenerateurECSService;
    let httpMock: HttpTestingController;
    let elemDefault: ITD014GenerateurECS;
    let expectedResult: ITD014GenerateurECS | ITD014GenerateurECS[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TD014GenerateurECSService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TD014GenerateurECS(0, 'AAAAAAA', 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TD014GenerateurECS', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TD014GenerateurECS()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TD014GenerateurECS', () => {
        const returnedFromService = Object.assign(
          {
            td013InstallationEcs: 'BBBBBB',
            tr004TypeEnergie: 'BBBBBB',
            volumeStockage: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TD014GenerateurECS', () => {
        const returnedFromService = Object.assign(
          {
            td013InstallationEcs: 'BBBBBB',
            tr004TypeEnergie: 'BBBBBB',
            volumeStockage: 1,
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

      it('should delete a TD014GenerateurECS', () => {
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
