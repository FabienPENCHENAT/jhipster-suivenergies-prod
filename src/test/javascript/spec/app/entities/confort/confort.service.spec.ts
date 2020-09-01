import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ConfortService } from 'app/entities/confort/confort.service';
import { IConfort, Confort } from 'app/shared/model/confort.model';

describe('Service Tests', () => {
  describe('Confort Service', () => {
    let injector: TestBed;
    let service: ConfortService;
    let httpMock: HttpTestingController;
    let elemDefault: IConfort;
    let expectedResult: IConfort | IConfort[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ConfortService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Confort(0, false, false, false, false, false, 0, 0, 0, false, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Confort', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Confort()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Confort', () => {
        const returnedFromService = Object.assign(
          {
            installationElectrique: true,
            installationGaz: true,
            installationHumidite: true,
            installationPortesFenetres: true,
            chauffageHiver: true,
            surfaceChauffee: 1,
            temperatureHiverSejour: 1,
            temperatureHiverChambres: 1,
            climEte: true,
            temperatureEteSejour: 1,
            temperatureEteChambres: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Confort', () => {
        const returnedFromService = Object.assign(
          {
            installationElectrique: true,
            installationGaz: true,
            installationHumidite: true,
            installationPortesFenetres: true,
            chauffageHiver: true,
            surfaceChauffee: 1,
            temperatureHiverSejour: 1,
            temperatureHiverChambres: 1,
            climEte: true,
            temperatureEteSejour: 1,
            temperatureEteChambres: 1,
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

      it('should delete a Confort', () => {
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
