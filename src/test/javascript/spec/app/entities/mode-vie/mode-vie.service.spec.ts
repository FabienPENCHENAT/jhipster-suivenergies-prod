import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ModeVieService } from 'app/entities/mode-vie/mode-vie.service';
import { IModeVie, ModeVie } from 'app/shared/model/mode-vie.model';

describe('Service Tests', () => {
  describe('ModeVie Service', () => {
    let injector: TestBed;
    let service: ModeVieService;
    let httpMock: HttpTestingController;
    let elemDefault: IModeVie;
    let expectedResult: IModeVie | IModeVie[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ModeVieService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ModeVie(0, 0, false, false, false, false, false, false, false, false, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ModeVie', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ModeVie()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ModeVie', () => {
        const returnedFromService = Object.assign(
          {
            nbPersonnes: 1,
            presenceMatinSemaine: true,
            presenceMatinWE: true,
            presenceAMSemaine: true,
            presenceAMWE: true,
            presenceSoirSemaine: true,
            presenceSoirWE: true,
            presenceNuitSemaine: true,
            presenceNuitWE: true,
            semainesAbsenceHiver: 1,
            semainesAbsenceEte: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ModeVie', () => {
        const returnedFromService = Object.assign(
          {
            nbPersonnes: 1,
            presenceMatinSemaine: true,
            presenceMatinWE: true,
            presenceAMSemaine: true,
            presenceAMWE: true,
            presenceSoirSemaine: true,
            presenceSoirWE: true,
            presenceNuitSemaine: true,
            presenceNuitWE: true,
            semainesAbsenceHiver: 1,
            semainesAbsenceEte: 1,
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

      it('should delete a ModeVie', () => {
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
